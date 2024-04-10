package com.nte.financecore.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;

    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    @OneToOne
    @JoinColumn(name = "rating_id")
    private Rating rating;

    @OneToMany(mappedBy = "user")
    private List<Research> researchList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<EvaluationStatus> evaluationStatusList = new ArrayList<>();

    public void setRating(Rating rating){
        this.rating = rating;
        rating.setUser(this);
    }

    public void addResearch(Research research){
        this.researchList.add(research);
    }

    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    public void addEvaluationStatus(EvaluationStatus evaluationStatus){
        this.evaluationStatusList.add(evaluationStatus);
    }

    @Builder
    public User(String username, String password, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, String role, Rating rating) {
        this.username = username;
        this.password = password;

        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;

        this.role = role;

        this.setRating(rating);
    }
}
