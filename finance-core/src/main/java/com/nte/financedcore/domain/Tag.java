package com.nte.financedcore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String division;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<StockTag> stockTagList = new ArrayList<>();

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<ResearchTag> researchTagList = new ArrayList<>();

    public void addStockTag(StockTag stockTag){
        this.stockTagList.add(stockTag);
        stockTag.setTag(this);
    }

    public void addResearchTag(ResearchTag researchTag){
        this.researchTagList.add(researchTag);
    }

    public Tag(String name, String division, StockTag stockTag){
        this.name = name;
        this.division = division;
        addStockTag(stockTag);
    }
}
