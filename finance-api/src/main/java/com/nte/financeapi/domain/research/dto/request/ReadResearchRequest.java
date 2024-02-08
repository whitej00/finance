package com.nte.financeapi.domain.research.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadResearchRequest {

    private List<Long> tagIdList;
}