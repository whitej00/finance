package com.nte.financecore.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "rating")
    private User user;

    @OneToMany(mappedBy = "rating")
    private List<DailyRating> dailyRatingList = new ArrayList<>();

    private Long score;

    public void addDailyRating(DailyRating dailyRating){

        this.dailyRatingList.add(dailyRating);
    }

    public void setUser(User user){

        this.user = user;
    }

    @Builder
    public Rating() {

        this.score = 0L;
    }
}
