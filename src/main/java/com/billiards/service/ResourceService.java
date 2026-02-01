package com.billiards.service;

import com.billiards.domain.ResourceItem;
import com.billiards.repository.ResourceItemRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    private final ResourceItemRepository resourceItemRepository;

    public ResourceService(ResourceItemRepository resourceItemRepository) {
        this.resourceItemRepository = resourceItemRepository;
    }

    public ResourceItem create(ResourceItem resourceItem) {
        return resourceItemRepository.save(resourceItem);
    }

    public List<ResourceItem> findAll() {
        return resourceItemRepository.findAll();
    }
}
