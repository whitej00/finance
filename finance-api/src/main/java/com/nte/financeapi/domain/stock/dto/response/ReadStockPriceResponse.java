package com.nte.financeapi.domain.stock.dto.response;

import com.nte.financecore.domain.StockPrice;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadStockPriceResponse {

    private String stockCode;
    private String name;
    private Long marketCap;
    private List<StockPrice> stockPriceList;

    @Builder
    public ReadStockPriceResponse(Long marketCap, List<StockPrice> stockPriceList) {
        this.stockCode = stockCode;
        this.name = name;
        this.marketCap = marketCap;
        this.stockPriceList = stockPriceList;
    }
}