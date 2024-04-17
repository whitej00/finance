package com.nte.financeapi.domain.stock.service;

import com.nte.financeapi.domain.stock.dto.response.ReadStockPriceResponse;
import com.nte.financeapi.domain.stock.dto.response.ReadStockResponse;
import com.nte.financecore.domain.Stock;
import com.nte.financecore.domain.StockPrice;
import com.nte.financecore.repository.StockPriceRepository;
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
    private final StockPriceRepository stockPriceRepository;

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

    public ReadStockResponse readStock(Long id){
        Stock stock = stockRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("comment doesn't exist")
        );

        List<StockPrice> stockPriceList = stockPriceRepository.findAllByStockId(id);
        List<ReadStockPriceResponse> readStockPriceResponseList = stockPriceList.stream().
                map(stockPrice -> ReadStockPriceResponse.builder()
                        .baseDate(stockPrice.getBaseDate())
                        .openingPrice(stockPrice.getOpeningPrice())
                        .closingPrice(stockPrice.getOpeningPrice())
                        .lowPrice(stockPrice.getLowPrice())
                        .highPrice(stockPrice.getHighPrice())
                        .tradeVolume(stockPrice.getTradeVolume())
                        .tradeValue(stockPrice.getTradeValue())
                        .marketCap(stockPrice.getMarketCap())
                        .build())
                .toList();

        return ReadStockResponse.builder()
                .id(stock.getId())
                .stockCode(stock.getStockCode())
                .name(stock.getName())
                .marketCap(stock.getMarketCap())
                .readStockPriceResponseList(readStockPriceResponseList)
                .build();
    }

}
