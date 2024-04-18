package com.nte.writer;

import com.nte.dto.StockDto;
import com.nte.dto.StockPriceDto;
import com.nte.financecore.domain.Stock;
import com.nte.financecore.domain.StockPrice;
import com.nte.financecore.repository.StockPriceRepository;
import com.nte.financecore.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StockPriceWriter implements ItemWriter<StockDto> {

    private final StockRepository stockRepository;
    private final StockPriceRepository stockPriceRepository;

    @Override
    public void write(Chunk<? extends StockDto> chunk) throws Exception {
        for (StockDto stockDto : chunk) {
            List<StockPriceDto> stockPriceDtoList = stockDto.getStockPriceDtoList();

            Stock stock = stockRepository.findById(stockDto.getId()).get();

            for (StockPriceDto dto : stockPriceDtoList){
                StockPrice stockPrice = new StockPrice(
                        dto.getBaseDate(), dto.getOpeningPrice(), dto.getClosingPrice(),
                        dto.getLowPrice(), dto.getHighPrice(), dto.getTradeVolume(),
                        dto.getTradeValue(), dto.getMarketCap()
                );

                stockPrice.setStock(stock);
                stockPriceRepository.save(stockPrice);
            }
        }
    }
}
