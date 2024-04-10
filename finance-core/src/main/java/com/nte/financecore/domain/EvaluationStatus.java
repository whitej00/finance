package com.nte.financecore.domain;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "research_id")
    private Research research;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    private Boolean evaluated;

    private LocalDate targetRangeStart;
    private LocalDate targetRangeEnd;

    private Long targetPrice;
    private Long marketPrice;

    public void setUser(User user){
        this.user = user;
        user.addEvaluationStatus(this);
    }

    public void setStock(Stock stock){
        this.stock = stock;
        stock.addEvaluationStatus(this);
    }

    public void setResearch(Research research){
        this.research = research;
        research.setEvaluationStatus(this);
    }

    @Builder
    public EvaluationStatus(User user, Stock stock, Research research, Boolean evaluated, LocalDate targetRangeStart, LocalDate targetRangeEnd, Long targetPrice, Long marketPrice){
        this.evaluated = evaluated;
        this.targetRangeStart = targetRangeStart;
        this.targetRangeEnd = targetRangeEnd;
        this.targetPrice = targetPrice;
        this.marketPrice = marketPrice;

        this.setUser(user);
        this.setStock(stock);
        this.setResearch(research);
    }
}
