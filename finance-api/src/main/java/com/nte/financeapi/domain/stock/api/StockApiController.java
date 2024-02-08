package com.nte.financeapi.domain.stock.api;

import com.nte.financeapi.global.common.response.Response;
import com.nte.financeapi.domain.stock.dto.response.ReadStockListResponse;
import com.nte.financeapi.domain.stock.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
@Tag(name = "Stock", description = "Stock API")
public class StockApiController {

    private final StockService stockService;

    @GetMapping
    public Response<ReadStockListResponse> stockList(){
        ReadStockListResponse response = stockService.stockList();

        return new Response<>(response);
    }
}
