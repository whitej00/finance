package com.nte.stockPrice.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockDto {

    Long id;
    String json;

    private List<StockPriceDto> stockPriceDtoList = new ArrayList<>();

    public void setStockPriceDtoList(List<StockPriceDto> stockPriceDtoList) {
        this.stockPriceDtoList = stockPriceDtoList;
    }

    @Builder
    public StockDto(Long id, String json) {
        this.id = id;
        this.json = json;
    }
}
