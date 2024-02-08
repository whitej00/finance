package com.nte.financeapi.domain.research.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateResearchRequest {

    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDate targetRangeStart;
    private LocalDate targetRangeEnd;
    private Long targetPrice;
    private List<Long> researchTagIdList;
}
