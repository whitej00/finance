package com.nte.stockPrice.dto;

import com.nte.financedcore.domain.EvaluationStatus;
import com.nte.financedcore.domain.Research;
import com.nte.financedcore.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationStatusDto {

    EvaluationStatus evaluationStatus;

    Research research;

    User user;

    List<StockPriceDto> stockPriceDtoList;

    @Builder
    public EvaluationStatusDto(EvaluationStatus evaluationStatus, Research research, User user, List<StockPriceDto> stockPriceDtoList) {
        this.evaluationStatus = evaluationStatus;
        this.research = research;
        this.user = user;
        this.stockPriceDtoList = stockPriceDtoList;
    }
}
