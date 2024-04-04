package com.nte.financedcore.repository;

import com.nte.financedcore.domain.EvaluationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationStatusRepository extends JpaRepository<EvaluationStatus, Long> {
}
