package com.nte.financeapi.domain.stock.dto.response;

import com.nte.financecore.domain.StockPrice;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadStockResponse {

    private Long id;
    private String stockCode;
    private String name;
    private Long marketCap;
    private List<ReadStockPriceResponse> readStockPriceResponseList = new ArrayList<>();

    @Builder
    public ReadStockResponse(Long id, String stockCode, String name, Long marketCap, List<ReadStockPriceResponse> readStockPriceResponseList) {
        this.id = id;
        this.stockCode = stockCode;
        this.name = name;
        this.marketCap = marketCap;
        this.readStockPriceResponseList = readStockPriceResponseList;
    }
}
