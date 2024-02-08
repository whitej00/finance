package com.nte.financeapi.domain.stock.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockDto {

    private Long id;
    private String stockCode;
    private String name;
    private Long marketCap;

    @Builder
    public StockDto(Long id, String stockCode, String name, Long marketCap) {
        this.id = id;
        this.stockCode = stockCode;
        this.name = name;
        this.marketCap = marketCap;
    }
}