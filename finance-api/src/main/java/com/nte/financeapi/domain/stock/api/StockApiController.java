package com.nte.financeapi.domain.stock.api;

import com.nte.financeapi.domain.research.dto.response.ReadResearchResponse;
import com.nte.financeapi.domain.research.service.ResearchService;
import com.nte.financeapi.domain.stock.dto.response.ReadStockResponse;
import com.nte.financeapi.domain.stock.service.StockService;
import com.nte.financeapi.global.common.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
@Tag(name = "Stock", description = "Stock API")
public class StockApiController {

    private final StockService stockService;
    private final ResearchService researchService;

    @GetMapping
    @Operation(summary = "Read All Stock", description = "모든 Stock 조회, But StockPrice는 조회 하지 않음")
    public Response<List<ReadStockResponse>> readStockList(){
        List<ReadStockResponse> readStockResponseList = stockService.readStockList();

        return new Response<>(readStockResponseList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Read Stock by id", description = "id로 Stock 조회")
    public Response<ReadStockResponse> readStock(@RequestParam Long id){
        ReadStockResponse readStockResponse = stockService.readStock(id);

        return new Response<>(readStockResponse);
    }

    @GetMapping("/{id}/researches")
    @Operation(summary = "Read Research that Stock has", description = "Stock이 가진 Research 조회")
    public Response<List<ReadResearchResponse>> readResearchListByStock(@RequestParam Long id){

        List<ReadResearchResponse> readResearchResponseList = researchService.readResearchListByStock(id);

        return new Response<>(readResearchResponseList);
    }
}
