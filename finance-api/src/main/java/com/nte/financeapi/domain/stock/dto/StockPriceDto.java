package com.nte.financeapi.domain.stock.dto;

import com.nte.financecore.domain.StockPrice;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockPriceDto {

    private String stockCode;
    private String name;
    private Long marketCap;
    private List<StockPrice> stockPriceList;

    @Builder
    public StockPriceDto(String stockCode, String name, Long marketCap, List<StockPrice> stockPriceList) {
        this.stockCode = stockCode;
        this.name = name;
        this.marketCap = marketCap;
        this.stockPriceList = stockPriceList;
    }
}
