package com.nte.financedcore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public void setTag(Tag tag){
        this.tag = tag;
    }

    public StockTag(Stock stock){
        this.stock = stock;
        stock.addStockTag(this);
    }

}
