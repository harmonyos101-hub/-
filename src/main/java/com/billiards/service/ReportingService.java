package com.billiards.service;

import com.billiards.domain.Bill;
import com.billiards.domain.BilliardTable;
import com.billiards.repository.BillRepository;
import com.billiards.repository.BilliardTableRepository;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ReportingService {
    private final BillRepository billRepository;
    private final BilliardTableRepository tableRepository;

    public ReportingService(BillRepository billRepository, BilliardTableRepository tableRepository) {
        this.billRepository = billRepository;
        this.tableRepository = tableRepository;
    }

    public Map<String, Object> generateSummary() {
        List<Bill> bills = billRepository.findAll();
        BigDecimal totalRevenue = bills.stream()
                .map(Bill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<BilliardTable> tables = tableRepository.findAll();
        long inUse = tables.stream().filter(table -> table.getStatus() != null && table.getStatus().name().equals("IN_USE")).count();

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalRevenue", totalRevenue);
        summary.put("totalTables", tables.size());
        summary.put("tablesInUse", inUse);
        summary.put("totalBills", bills.size());
        return summary;
    }
}
