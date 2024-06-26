package com.nte.chunk;

import com.nte.dto.StockDto;
import com.nte.financecore.domain.Stock;
import com.nte.financecore.repository.StockPriceRepository;
import com.nte.financecore.repository.StockRepository;
import com.nte.openapi.MyDataApiService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class StockPriceReader implements ItemReader<StockDto> {

    private final StockRepository stockRepository;
    private final StockPriceRepository stockPriceRepository;
    private final MyDataApiService mydataApiService;
    private List<Stock> stockList;
    private Iterator<Stock> iterator;

//    @PostConstruct
//    private void initializeStocks() {
//
//        this.stockList = stockRepository.findAll();
//        this.iterator = stockList.iterator();
//    }


    @Override
    public StockDto read() throws Exception {
        if(iterator == null){
            this.stockList = stockRepository.findAll();
            this.iterator = stockList.iterator();
        }

        if (iterator.hasNext()) {
            Stock stock = iterator.next();

            LocalDate beginBasDtLocalDate = stockPriceRepository.getLatestLocalDate(stock.getId());
            if(beginBasDtLocalDate == null)
                beginBasDtLocalDate = LocalDate.of(1900, 1, 1);

            Long id = stock.getId();
            String name = stock.getName();
            String beginBasDt = beginBasDtLocalDate.plusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE);

            System.out.println("name = " + name);
            System.out.println("beginBasDt = " + beginBasDt);

            Map<String, String> map = new HashMap<>();

            map.put("itmsNm", name);
            map.put("beginBasDt", beginBasDt);
            map.put("numOfRows", "30");

            return StockDto.builder().
                    id(id).
                    json(mydataApiService.read(map)).
                    build();
        } else {

            return null;
        }
    }
}



