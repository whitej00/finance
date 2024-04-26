package com.nte.tasklet;

import com.nte.financecore.domain.Stock;
import com.nte.financecore.repository.StockRepository;
import com.nte.openapi.MyDataApiService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class StockTasklet implements Tasklet {

    private final StockRepository stockRepository;
    private final MyDataApiService mydataApiService;
    private final String MARKET_CAPITALIZATION = "5000000000000"; // 5조

    private Set<String> stockNameHashSet = new HashSet<>();

    @PostConstruct
    private void initializeStocks() {

        this.stockNameHashSet = stockRepository.findAllDistinctNames();
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("basDt", "20231010");
        map.put("numOfRows", "10");
        map.put("beginMrktTotAmt", MARKET_CAPITALIZATION);

        String json = mydataApiService.read(map);

        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = (JSONObject)jsonParser.parse(json);
        JSONObject response = (JSONObject) jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");
        JSONObject items = (JSONObject) body.get("items");
        JSONArray item = (JSONArray) items.get("item");

        List<Stock> stockList = new ArrayList<>();
        for (Object o : item) {
            JSONObject obj = (JSONObject) o;

            String name = (String) obj.get("itmsNm");
            if(stockNameHashSet.contains(name))
                    continue;

            System.out.println("name = " + name);
            String stockCode = (String) obj.get("srtnCd");
            Long marketCap = Long.parseLong((String) obj.get("mrktTotAmt"));

            stockList.add(Stock.builder()
                    .stockCode(stockCode)
                    .name(name)
                    .marketCap(marketCap)
                    .build());
        }

        stockRepository.saveAll(stockList);

        return RepeatStatus.FINISHED;
    }
}
