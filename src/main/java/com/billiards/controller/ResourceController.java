package com.billiards.controller;

import com.billiards.domain.ResourceItem;
import com.billiards.service.ResourceService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResourceItem create(@RequestBody ResourceItem resourceItem) {
        return resourceService.create(resourceItem);
    }

    @GetMapping
    public List<ResourceItem> list() {
        return resourceService.findAll();
    }
}
