package com.nte.financecore.repository;

import com.nte.financecore.domain.Research;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResearchRepository extends JpaRepository<Research, Long> {

    List<Research> findAllByStockId(Long id);

    List<Research> findAllByUserId(Long id);
}
