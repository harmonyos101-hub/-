package com.billiards.controller;

import com.billiards.domain.BilliardTable;
import com.billiards.domain.Enums;
import com.billiards.dto.TableRequest;
import com.billiards.service.BilliardTableService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tables")
public class BilliardTableController {
    private final BilliardTableService tableService;

    public BilliardTableController(BilliardTableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    public BilliardTable create(@Valid @RequestBody TableRequest request) {
        return tableService.create(request);
    }

    @GetMapping
    public List<BilliardTable> list() {
        return tableService.findAll();
    }

    @PatchMapping("/{id}/status/{status}")
    public BilliardTable updateStatus(@PathVariable Long id, @PathVariable Enums.TableStatus status) {
        return tableService.updateStatus(id, status);
    }
}
