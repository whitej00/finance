package com.nte.financedcore.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationStatus {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "research_id")
    private Research research;

    private Boolean evaluated;

    private LocalDate targetRangeStart;
    private LocalDate targetRangeEnd;
    private Long targetPrice;

    private Long marketPrice;

    public void setResearch(Research research){
        this.research = research;
    }

    @Builder
    public EvaluationStatus(Boolean evaluated, LocalDate targetRangeStart, LocalDate targetRangeEnd, Long targetPrice, Long marketPrice){
        this.evaluated = evaluated;
        this.targetRangeStart = targetRangeStart;
        this.targetRangeEnd = targetRangeEnd;
        this.targetPrice = targetPrice;
        this.marketPrice = marketPrice;
    }
}
