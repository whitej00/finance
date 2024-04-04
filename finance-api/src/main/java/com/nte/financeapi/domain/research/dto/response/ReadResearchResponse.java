package com.nte.financeapi.domain.research.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadResearchResponse {

    private String username;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDate targetRangeStart;
    private LocalDate targetRangeEnd;
    private Long targetPrice;

    @Builder
    public ReadResearchResponse(String username, String title, String content, LocalDateTime createdDate, LocalDate targetRangeStart, LocalDate targetRangeEnd, Long targetPrice) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.targetRangeStart = targetRangeStart;
        this.targetRangeEnd = targetRangeEnd;
        this.targetPrice = targetPrice;
    }
}
