package com.billiards.repository;

import com.billiards.domain.BilliardTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilliardTableRepository extends JpaRepository<BilliardTable, Long> {
}
