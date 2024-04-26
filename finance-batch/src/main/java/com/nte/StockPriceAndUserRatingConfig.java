package com.nte;

import com.nte.dto.EvaluationStatusDto;
import com.nte.dto.StockDto;
import com.nte.chunk.StockPriceProcessor;
import com.nte.chunk.UserRatingProcessor;
import com.nte.chunk.StockPriceReader;
import com.nte.chunk.UserRatingReader;
import com.nte.tasklet.StockTasklet;
import com.nte.chunk.StockPriceWriter;
import com.nte.chunk.UserRatingWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;


@Slf4j
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.nte.*"})
@EntityScan(basePackages = {"com.nte.*"})
@EnableJpaRepositories(basePackages = {"com.nte.*"})
public class StockPriceAndUserRatingConfig {

    @Bean
    public Job stockPriceAndUserRatingJob(JobRepository jobRepository,
                                          Step stockStep, Step stockPriceStep, Step userRatingStep) {
        return new JobBuilder("stockPriceAndUserRatingJob", jobRepository)
                .start(stockStep)
                .next(stockPriceStep)
                .next(userRatingStep)
                .build();
    }

    @Bean
    public Step stockStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                          StockTasklet stockTasklet){
        return new StepBuilder("stockStep", jobRepository)
                .tasklet(stockTasklet, transactionManager)
                .build();
    }

    @Bean
    public Step stockPriceStep(
            JobRepository jobRepository, PlatformTransactionManager transactionManager,
            StockPriceReader stockPriceReader, StockPriceProcessor stockPriceProcessor, StockPriceWriter stockPriceWriter) {
        return new StepBuilder("stockPriceStep", jobRepository)
                .<StockDto, StockDto>chunk(10, transactionManager)
                .reader(stockPriceReader)
                .processor(stockPriceProcessor)
                .writer(stockPriceWriter)
                .build();
    }

    @Bean
    public Step userRatingStep(
            JobRepository jobRepository, PlatformTransactionManager transactionManager,
            UserRatingReader userRatingReader, UserRatingProcessor userRatingProcessor, UserRatingWriter userRatingWriter) {
        return new StepBuilder("userRatingStep", jobRepository)
                .<EvaluationStatusDto, EvaluationStatusDto>chunk(10, transactionManager)
                .reader(userRatingReader)
                .processor(userRatingProcessor)
                .writer(userRatingWriter)
                .build();
    }

}
