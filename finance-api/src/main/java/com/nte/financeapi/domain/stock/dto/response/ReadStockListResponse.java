package com.nte.financeapi.domain.stock.dto.response;

import com.nte.financeapi.domain.stock.dto.StockDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadStockListResponse {

    List<StockDto> stockDtoList;

    @Builder
    public ReadStockListResponse(List<StockDto> stockDtoList) {
        this.stockDtoList = stockDtoList;
    }
}
