package com.nte.stockPrice.openapi;

import com.nte.financedcore.domain.Stock;
import com.nte.financedcore.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MyDataStockService {
    /**
     * ONE-OFF class
     * This code serves to receive listed stocks.
     * */
    private final StockRepository stockRepository;
    private final MyDataCommon mydataCommon;

    private final String MARKET_CAPITALIZATION = "5000000000000"; // 5조

    /**
     * get company.
     * */
    public void read() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("basDt", "20231010");
        map.put("numOfRows", "10");
        map.put("beginMrktTotAmt", MARKET_CAPITALIZATION);

        String json = mydataCommon.read(map);
        List<Stock> stockList = process(json);
        write(stockList);
    }

    public List<Stock> process(String result) throws Exception{
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
        JSONObject response = (JSONObject) jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");
        JSONObject items = (JSONObject) body.get("items");
        JSONArray item = (JSONArray) items.get("item");

        List<Stock> stockList = new ArrayList<>();
        for (Object o : item) {
            JSONObject obj = (JSONObject) o;
            String stockCode = (String) obj.get("srtnCd");
            String name = (String) obj.get("itmsNm");
            Long marketCap = Long.parseLong((String) obj.get("mrktTotAmt"));

            stockList.add(new Stock(stockCode, name, marketCap));
        }

        return stockList;
    }

    @Transactional
    public void write(List<Stock> stockList){

        stockRepository.saveAll(stockList);
    }
}
