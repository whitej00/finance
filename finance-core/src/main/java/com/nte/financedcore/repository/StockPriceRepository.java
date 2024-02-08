package com.nte.financedcore.repository;

import com.nte.financedcore.domain.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

    @Nullable
    @Query("select max(s.baseDate) from StockPrice s where s.stock.id = :id")
    LocalDate getLatestLocalDate(@Param("id") Long id);
}
