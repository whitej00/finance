package com.nte.financedcore.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    public void setStock(Stock stock){
        this.stock = stock;
        stock.getPriceList().add(this);
    }

    public StockPrice(LocalDate baseDate, Long openingPrice, Long closingPrice, Long lowPrice, Long highPrice, Long tradeVolume, Long tradeValue, Long marketCap) {
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
