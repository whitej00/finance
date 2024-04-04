package com.nte;

import com.nte.financedcore.domain.Stock;
import com.nte.financedcore.domain.StockPrice;
import com.nte.financedcore.repository.StockPriceRepository;
import com.nte.financedcore.repository.StockRepository;
import com.nte.stockPrice.StockPriceProcessor;
import com.nte.stockPrice.StockPriceReader;
import com.nte.stockPrice.dto.StockDto;
import com.nte.stockPrice.dto.StockPriceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;


@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.nte.*"})
@EntityScan(basePackages = {"com.nte.*"})
@EnableJpaRepositories(basePackages = {"com.nte.*"})
public class StockPriceConfig {

    private final StockRepository stockRepository;
    private final StockPriceRepository stockPriceRepository;

    @Bean
    public Job stockPriceJob(JobRepository jobRepository, Step firstStep) {
        return new JobBuilder("stockPriceJob", jobRepository)
                .start(firstStep)
                .build();
    }

    @Bean
    public Step stockPriceStep(
            JobRepository jobRepository, PlatformTransactionManager transactionManager,
            StockPriceReader stockPriceReader, StockPriceProcessor stockPriceProcessor) {
        return new StepBuilder("stockPriceStep", jobRepository)
                .<StockDto, StockDto>chunk(10, transactionManager)
                .reader(stockPriceReader)
                .processor(stockPriceProcessor)
                .writer(itemWriter())
                .build();
    }

    @Bean
    public ItemWriter<StockDto> itemWriter() {
        return items -> {
            for (StockDto stockDto : items) {
                List<StockPriceDto> stockPriceDtoList = stockDto.getStockPriceDtoList();

                Stock stock = stockRepository.findById(stockDto.getId()).get();
                System.out.println("stock.getName() = " + stock.getName());

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
        };
    }
}
