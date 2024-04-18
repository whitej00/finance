package com.nte;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@RequiredArgsConstructor
@SpringBootApplication
public class BatchApplication {

    private final JobLauncher jobLauncher;
    private final Job job;

    public static void main(String[] args) {

        SpringApplication.run(BatchApplication.class, args);
    }
}