package com.itsupport.repository;

import com.itsupport.model.Breakdown;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreakdownRepository extends JpaRepository<Breakdown, Long> {
}
