package com.nte.financeapi.domain.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DailyRatingResponse {

    private Long score;
    private LocalDate updatedDate;

    @Builder
    public DailyRatingResponse(Long score, LocalDate updatedDate) {
        this.score = score;
        this.updatedDate = updatedDate;
    }
}
