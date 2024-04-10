package com.nte;

import com.nte.stockPrice.openapi.MyDataStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;

@RequiredArgsConstructor
@SpringBootApplication
public class BatchApplication implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job job;
    private final MyDataStockService myDataStockService;

    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        myDataStockService.read();

        JobParameters params = new JobParametersBuilder()
                .addLocalDate("time", LocalDate.now())
                .toJobParameters();

        jobLauncher.run(job, params);
    }
}