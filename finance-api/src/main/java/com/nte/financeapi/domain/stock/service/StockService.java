package com.nte.financeapi.domain.stock.service;

import com.nte.financeapi.domain.stock.dto.StockDto;
import com.nte.financeapi.domain.stock.dto.response.ReadStockListResponse;
import com.nte.financedcore.domain.Stock;
import com.nte.financedcore.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {

    private final StockRepository stockRepository;

    public ReadStockListResponse stockList(){
        List<Stock> stocks = stockRepository.findAll();

        List<StockDto> stockDtoList = stocks.stream()
                .map(stock -> StockDto.builder()
                        .id(stock.getId())
                        .stockCode(stock.getStockCode())
                        .name(stock.getStockCode())
                        .marketCap(stock.getMarketCap())
                        .build())
                .toList();

        return ReadStockListResponse.builder()
                .stockDtoList(stockDtoList)
                .build();
    }

//    public StockPriceDto stockPrice(Long id){
//        Stock stock = stockRepository.findById(id).get();
//    }

}
