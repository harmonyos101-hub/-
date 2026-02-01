package com.billiards.service;

import com.billiards.domain.Shift;
import com.billiards.repository.ShiftRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public Shift create(Shift shift) {
        return shiftRepository.save(shift);
    }

    public List<Shift> findAll() {
        return shiftRepository.findAll();
    }
}
