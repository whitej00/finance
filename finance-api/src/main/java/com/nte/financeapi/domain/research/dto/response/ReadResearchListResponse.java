package com.nte.financeapi.domain.research.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadResearchListResponse {

    private List<ReadResearchResponse> readResearchResponseDtoList;

    @Builder
    public ReadResearchListResponse(List<ReadResearchResponse> readResearchResponseDtoList) {
        this.readResearchResponseDtoList = readResearchResponseDtoList;
    }
}
