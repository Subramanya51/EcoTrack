package com.SmartGarbageCollection.GarbageCollection.Service;

import com.SmartGarbageCollection.GarbageCollection.DTO.AdminRegisterDTO;
import com.SmartGarbageCollection.GarbageCollection.Entity.Admin;
import com.SmartGarbageCollection.GarbageCollection.Entity.Role;
import com.SmartGarbageCollection.GarbageCollection.Repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String registerAdmin(AdminRegisterDTO dto) {

        // 🔒 Check duplicate email (case-insensitive)
        if (adminRepository.existsByEmailIgnoreCase(dto.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Admin already exists with this email"
            );
        }

        // 🔹 Create admin
        Admin admin = Admin.builder()
                .name(dto.getName())
                .email(dto.getEmail().toLowerCase()) // normalize
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.ROLE_ADMIN)
                .build(); // createdAt handled by @PrePersist

        adminRepository.save(admin);

        return "Admin registered successfully";
    }
}


//package com.SmartGarbageCollection.GarbageCollection.Service;
//import com.SmartGarbageCollection.GarbageCollection.DTO.AdminRegisterDTO;
//import com.SmartGarbageCollection.GarbageCollection.Entity.Admin;
//import com.SmartGarbageCollection.GarbageCollection.Entity.Role;
//import com.SmartGarbageCollection.GarbageCollection.Repository.AdminRepository;
//import com.SmartGarbageCollection.GarbageCollection.Service.AdminService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class AdminServiceImpl implements AdminService {
//
//    private final AdminRepository adminRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public String registerAdmin(AdminRegisterDTO dto) {
//
//        // Check duplicate email
//        if (adminRepository.existsByEmail(dto.getEmail())) {
//            throw new RuntimeException("Admin already exists");
//        }
//
//        // Create admin
//        Admin admin = Admin.builder()
//                .name(dto.getName())
//                .email(dto.getEmail())
//                .password(passwordEncoder.encode(dto.getPassword()))
//                .role(Role.ROLE_ADMIN) // 🔐 Controlled here
//                .createdAt(LocalDateTime.now())
//                .build();
//
//        adminRepository.save(admin);
//
//        return "Admin registered successfully";
//    }
//}