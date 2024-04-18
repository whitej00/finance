package com.nte.processor;

import com.nte.dto.EvaluationStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRatingProcessor implements ItemProcessor<EvaluationStatusDto, EvaluationStatusDto> {

    @Override
    public EvaluationStatusDto process(EvaluationStatusDto dto) throws Exception{

//        JSONParser jsonParser = new JSONParser();
//
//        JSONObject jsonObject=(JSONObject)jsonParser.parse(dto.getJson());
//        JSONObject response=(JSONObject)jsonObject.get("response");
//        JSONObject body=(JSONObject)response.get("body");
//        JSONObject items=(JSONObject)body.get("items");
//        JSONArray item=(JSONArray)items.get("item");
//
//        List<StockPriceDto> stockPriceDtoList = new ArrayList<>();
//
//        for(Object o : item) {
//            JSONObject obj = (JSONObject) o;
//
//            LocalDate baseDate = LocalDate.parse((String) obj.get("basDt"), DateTimeFormatter.BASIC_ISO_DATE);
//            Long openingPrice = Long.parseLong((String) obj.get("mkp"));
//            Long closingPrice = Long.parseLong((String) obj.get("clpr"));
//            Long lowPrice = Long.parseLong((String) obj.get("lopr"));
//            Long highPrice = Long.parseLong((String) obj.get("hipr"));
//            Long tradeVolume = Long.parseLong((String) obj.get("trqu"));
//            Long tradeValue = Long.parseLong((String) obj.get("trPrc"));
//            Long marketCap = Long.parseLong((String) obj.get("mrktTotAmt"));
//
//            StockPriceDto stockPriceDto = new StockPriceDto(baseDate, openingPrice, closingPrice, lowPrice, highPrice, tradeVolume, tradeValue, marketCap);
//
//            stockPriceDtoList.add(stockPriceDto);
//        }
//        dto.setStockPriceDtoList(stockPriceDtoList);

        return dto;
    }
}
