package com.billiards.service;

import com.billiards.domain.BilliardTable;
import com.billiards.domain.Enums;
import com.billiards.dto.TableRequest;
import com.billiards.repository.BilliardTableRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BilliardTableService {
    private final BilliardTableRepository tableRepository;

    public BilliardTableService(BilliardTableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public BilliardTable create(TableRequest request) {
        BilliardTable table = new BilliardTable();
        table.setTableNumber(request.getTableNumber());
        table.setModel(request.getModel());
        table.setSize(request.getSize());
        return tableRepository.save(table);
    }

    public List<BilliardTable> findAll() {
        return tableRepository.findAll();
    }

    public BilliardTable findById(Long id) {
        return tableRepository.findById(id).orElseThrow();
    }

    public BilliardTable updateStatus(Long id, Enums.TableStatus status) {
        BilliardTable table = findById(id);
        table.setStatus(status);
        return tableRepository.save(table);
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }
}
