package com.nte.financeapi.domain.stock.service;

import com.nte.financeapi.domain.stock.dto.response.ReadStockResponse;
import com.nte.financecore.domain.Stock;
import com.nte.financecore.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {

    private final StockRepository stockRepository;

    public List<ReadStockResponse> readStockList(){
        List<Stock> stocks = stockRepository.findAll();

        return stocks.stream()
                .map(stock -> ReadStockResponse.builder()
                        .id(stock.getId())
                        .stockCode(stock.getStockCode())
                        .name(stock.getStockCode())
                        .marketCap(stock.getMarketCap())
                        .build())
                .toList();
    }

//    public ReadStockResponse raadStock(Long id){
//        Stock stock = stockRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("comment doesn't exist")
//        );
//
//        return ReadStockResponse.builder()
//                .
//    }

}
