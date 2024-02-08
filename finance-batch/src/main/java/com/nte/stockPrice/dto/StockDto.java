package com.nte.stockPrice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StockDto {

    Long id;
    String json;

    private List<StockPriceDto> stockPriceDtoList = new ArrayList<>();

    public StockDto(Long id, String json) {
        this.id = id;
        this.json = json;
    }
}
