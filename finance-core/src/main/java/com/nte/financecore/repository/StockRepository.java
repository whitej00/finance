package com.nte.financecore.repository;

import com.nte.financecore.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockRepository extends JpaRepository<Stock, Long> {

}
