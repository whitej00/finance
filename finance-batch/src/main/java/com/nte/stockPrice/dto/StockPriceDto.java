package com.nte.stockPrice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
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
