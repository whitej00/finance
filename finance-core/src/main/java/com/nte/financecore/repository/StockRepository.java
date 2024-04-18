package com.nte.financecore.repository;

import com.nte.financecore.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT DISTINCT s.name FROM Stock s")
    Set<String> findAllDistinctNames();
}
