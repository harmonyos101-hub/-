package com.billiards.service;

import com.billiards.domain.Role;
import com.billiards.domain.Staff;
import com.billiards.dto.StaffRequest;
import com.billiards.repository.RoleRepository;
import com.billiards.repository.StaffRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;

    public StaffService(StaffRepository staffRepository, RoleRepository roleRepository) {
        this.staffRepository = staffRepository;
        this.roleRepository = roleRepository;
    }

    public Staff create(StaffRequest request) {
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow();
        Staff staff = new Staff();
        staff.setName(request.getName());
        staff.setPhone(request.getPhone());
        staff.setRole(role);
        return staffRepository.save(staff);
    }

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }
}
