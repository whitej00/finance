package com.nte.financedcore.repository;

import com.nte.financedcore.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByName(String name);
}
