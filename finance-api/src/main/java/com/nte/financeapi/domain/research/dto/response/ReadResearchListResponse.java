package com.nte.financeapi.domain.research.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadResearchListResponse {

    private List<ReadResearchResponse> readResearchResponseList;

    @Builder
    public ReadResearchListResponse(List<ReadResearchResponse> readResearchResponseList) {
        this.readResearchResponseList = readResearchResponseList;
    }
}
