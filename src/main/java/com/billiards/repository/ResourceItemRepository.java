package com.billiards.repository;

import com.billiards.domain.ResourceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceItemRepository extends JpaRepository<ResourceItem, Long> {
}
