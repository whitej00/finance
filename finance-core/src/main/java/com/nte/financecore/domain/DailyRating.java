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
public class DailyRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rating_id")
    private Rating rating;

    private Long score;

    private LocalDate updatedDate;

    void setRating(Rating rating){
        this.rating = rating;
        rating.addDailyRating(this);
    }

    @Builder
    public DailyRating(Long score, LocalDate updatedDate, Rating rating) {
        this.score = score;
        this.updatedDate = updatedDate;

        this.setRating(rating);
    }
}
