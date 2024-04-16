package com.nte.financeapi.domain.stock.api;

import com.nte.financeapi.domain.stock.dto.response.ReadStockResponse;
import com.nte.financeapi.domain.stock.service.StockService;
import com.nte.financeapi.global.common.response.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
@Tag(name = "Stock", description = "Stock API")
public class StockApiController {

    private final StockService stockService;

    @GetMapping
    public Response<List<ReadStockResponse>> readStockList(){
        List<ReadStockResponse> response = stockService.readStockList();

        return new Response<>(response);
    }

//    @GetMapping("/{id}")
//    public Response<ReadStockResponse> readStock(@RequestParam Long id){
//
//        return stockService.
//
//    }
}
