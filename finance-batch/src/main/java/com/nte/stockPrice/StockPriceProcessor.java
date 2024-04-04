package com.nte.stockPrice;

import com.nte.stockPrice.dto.StockDto;
import com.nte.stockPrice.dto.StockPriceDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StockPriceProcessor implements ItemProcessor<StockDto, StockDto> {

    @Override
    public StockDto process(StockDto stockDto) throws Exception{

        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject=(JSONObject)jsonParser.parse(stockDto.getJson());
        JSONObject response=(JSONObject)jsonObject.get("response");
        JSONObject body=(JSONObject)response.get("body");
        JSONObject items=(JSONObject)body.get("items");
        JSONArray item=(JSONArray)items.get("item");

        List<StockPriceDto> stockPriceDtoList = new ArrayList<>();

        for(Object o : item) {
            JSONObject obj = (JSONObject) o;

            LocalDate baseDate = LocalDate.parse((String) obj.get("basDt"), DateTimeFormatter.BASIC_ISO_DATE);
            Long openingPrice = Long.parseLong((String) obj.get("mkp"));
            Long closingPrice = Long.parseLong((String) obj.get("clpr"));
            Long lowPrice = Long.parseLong((String) obj.get("lopr"));
            Long highPrice = Long.parseLong((String) obj.get("hipr"));
            Long tradeVolume = Long.parseLong((String) obj.get("trqu"));
            Long tradeValue = Long.parseLong((String) obj.get("trPrc"));
            Long marketCap = Long.parseLong((String) obj.get("mrktTotAmt"));

            stockPriceDtoList.add(StockPriceDto.builder().
                    baseDate(baseDate).
                    openingPrice(openingPrice).
                    closingPrice(closingPrice).
                    lowPrice(lowPrice).
                    highPrice(highPrice).
                    tradeVolume(tradeVolume).
                    tradeValue(tradeValue).
                    marketCap(marketCap).
                    build()
            );
        }

        stockDto.setStockPriceDtoList(stockPriceDtoList);

        return stockDto;
    }
}
