package com.nte.writer;

import com.nte.financecore.repository.StockPriceRepository;
import com.nte.financecore.repository.StockRepository;
import com.nte.dto.EvaluationStatusDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRatingWriter implements ItemWriter<EvaluationStatusDto> {

    private final StockRepository stockRepository;
    private final StockPriceRepository stockPriceRepository;

    @Override
    public void write(Chunk<? extends EvaluationStatusDto> chunk) throws Exception {
//        for (StockDto stockDto : chunk) {
//            List<StockPriceDto> stockPriceDtoList = stockDto.getStockPriceDtoList();
//
//            Stock stock = stockRepository.findById(stockDto.getId()).get();
//            System.out.println("stock.getName() = " + stock.getName());
//
//            for (StockPriceDto dto : stockPriceDtoList){
//                StockPrice stockPrice = new StockPrice(
//                        dto.getBaseDate(), dto.getOpeningPrice(), dto.getClosingPrice(),
//                        dto.getLowPrice(), dto.getHighPrice(), dto.getTradeVolume(),
//                        dto.getTradeValue(), dto.getMarketCap()
//                );
//
//                stockPrice.setStock(stock);
//                stockPriceRepository.save(stockPrice);
//            }
//        }
    }
}
