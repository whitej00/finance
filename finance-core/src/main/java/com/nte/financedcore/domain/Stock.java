package com.nte.financedcore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {

    @Id
    @GeneratedValue
    private Long id;
    private String stockCode;
    private String name;
    private Long marketCap;

    @OneToMany(mappedBy = "stock")
    private List<StockPrice> priceList = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    private List<StockTag> stockTagList = new ArrayList<>();

    public void addStockTag(StockTag stockTag){
        stockTagList.add(stockTag);
    }

    public Stock(String name) {
        this.name = name;
    }

    public Stock(String stockCode, String name, Long marketCap) {
        this.stockCode = stockCode;
        this.name = name;
        this.marketCap = marketCap;
    }
}
