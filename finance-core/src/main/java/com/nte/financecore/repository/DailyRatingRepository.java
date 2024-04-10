package com.nte.financecore.repository;

import com.nte.financecore.domain.DailyRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyRatingRepository extends JpaRepository<DailyRating, Long> {
}
