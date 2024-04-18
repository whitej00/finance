package com.nte.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockPriceDto {
    //날짜
    private LocalDate baseDate;
    //시가
    private Long openingPrice;
    //종가
    private Long closingPrice;
    //저가
    private Long lowPrice;
    //고가
    private Long highPrice;
    //거래량
    private Long tradeVolume;
    //거래 대금
    private Long tradeValue;
    //시가 총액
    private Long marketCap;

    @Builder
    public StockPriceDto(LocalDate baseDate, Long openingPrice, Long closingPrice, Long lowPrice, Long highPrice, Long tradeVolume, Long tradeValue, Long marketCap) {
        this.baseDate = baseDate;
        this.openingPrice = openingPrice;
        this.closingPrice = closingPrice;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.tradeVolume = tradeVolume;
        this.tradeValue = tradeValue;
        this.marketCap = marketCap;
    }
}
