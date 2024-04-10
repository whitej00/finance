package com.nte.financecore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Research {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @OneToOne(mappedBy = "research")
    private EvaluationStatus evaluationStatus;

    @OneToMany(mappedBy = "research")
    private List<Comment> commentList = new ArrayList<>();


    private String title;
    private String content;

    private LocalDateTime createdDate;

    private LocalDate targetRangeStart;
    private LocalDate targetRangeEnd;

    private Long targetPrice;

    public void setUser(User user){
        this.user = user;
        user.addResearch(this);
    }

    public void setStock(Stock stock){
        this.stock = stock;
        stock.addResearch(this);
    }

    public void setEvaluationStatus(EvaluationStatus evaluationStatus){
        this.evaluationStatus = evaluationStatus;
    }

    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    @Builder
    public Research(User user, Stock stock, String title, String content, LocalDateTime createdDate, LocalDate targetRangeStart, LocalDate targetRangeEnd, Long targetPrice) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.targetRangeStart = targetRangeStart;
        this.targetRangeEnd = targetRangeEnd;
        this.targetPrice = targetPrice;

        this.setUser(user);
        this.setStock(stock);
    }
}
