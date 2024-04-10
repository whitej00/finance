package com.nte.financecore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stockCode;
    private String name;
    private Long marketCap;

    @OneToMany(mappedBy = "stock")
    private List<StockPrice> stockPriceList = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    private List<Research> researchList = new ArrayList<>();

    @OneToMany(mappedBy = "stock")
    private List<EvaluationStatus> evaluationStatusList = new ArrayList<>();

    public void addStockPrice(StockPrice stockPrice){
        this.stockPriceList.add(stockPrice);
    }

    public void addResearch(Research research){
        this.researchList.add(research);
    }

    public void addEvaluationStatus(EvaluationStatus evaluationStatus){
        this.evaluationStatusList.add(evaluationStatus);
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
