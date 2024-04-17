package com.nte.financeapi.domain.user.dto.response;

import com.nte.financecore.domain.DailyRating;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ReadUserResponse {

    private Long id;
    private String username;

    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    private Long ratingScore;
    private List<DailyRating> dailyRatingList = new ArrayList<>();

    @Builder
    public ReadUserResponse(Long id, String username, LocalDateTime createdDateTime, LocalDateTime updatedDateTime, Long ratingScore, List<DailyRating> dailyRatingList) {
        this.id = id;
        this.username = username;
        this.createdDateTime = createdDateTime;
        this.updatedDateTime = updatedDateTime;
        this.ratingScore = ratingScore;
        this.dailyRatingList = dailyRatingList;
    }
}
