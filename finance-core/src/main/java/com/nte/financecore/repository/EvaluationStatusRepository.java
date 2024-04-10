package com.nte.financecore.repository;

import com.nte.financecore.domain.EvaluationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationStatusRepository extends JpaRepository<EvaluationStatus, Long> {
}
