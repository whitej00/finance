package com.nte.financeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.nte.*"})
@EntityScan(basePackages = {"com.nte.*"})
@EnableJpaRepositories(basePackages = {"com.nte.*"})
public class FinanceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceApiApplication.class, args);
    }
}