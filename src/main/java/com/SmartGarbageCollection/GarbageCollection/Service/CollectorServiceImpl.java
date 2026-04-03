package com.SmartGarbageCollection.GarbageCollection.Service;

import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorCreateDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorResponseDTO;
import com.SmartGarbageCollection.GarbageCollection.Entity.Admin;
import com.SmartGarbageCollection.GarbageCollection.Entity.Collector;
import com.SmartGarbageCollection.GarbageCollection.Entity.Role;
import com.SmartGarbageCollection.GarbageCollection.Entity.UserDetailImpl;
import com.SmartGarbageCollection.GarbageCollection.Repository.AdminRepository;
import com.SmartGarbageCollection.GarbageCollection.Repository.CollectorRepository;
import com.SmartGarbageCollection.GarbageCollection.Utilis.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CollectorServiceImpl implements CollectorService {

    private final CollectorRepository collectorRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;

    // 🔹 CREATE COLLECTOR
    @Override
    @Transactional
    public CollectorResponseDTO createCollector(CollectorCreateDTO dto, String adminEmail) {

        // 🔒 Check phone uniqueness
        if (collectorRepository.existsByPhone(dto.getPhone())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone already exists");
        }

        // 🔹 Generate unique collectorId
        String collectorId;
        do {
            int randomNum = ThreadLocalRandom.current().nextInt(1000, 10000);
            collectorId = "COLL-" + randomNum;
        } while (collectorRepository.existsByCollectorId(collectorId));

        // 🔹 Generate password
        String rawPassword = generateRandomPassword(6);

        // 🔹 Get admin (case-insensitive)
        Admin admin = adminRepository.findByEmailIgnoreCase(adminEmail)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found")
                );

        // 🔹 Create collector
        Collector collector = Collector.builder()
                .collectorId(collectorId)
                .name(dto.getName())
                .phone(dto.getPhone())
                .password(passwordEncoder.encode(rawPassword))
                .role(Role.ROLE_COLLECTOR)
                .createdAt(LocalDateTime.now())
                .createdBy(admin.getId())
                .build();

        collectorRepository.save(collector);

        return new CollectorResponseDTO(collectorId, rawPassword);
    }

    // 🔐 LOGIN
    @Override
    public String login(String collectorId, String password) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(collectorId, password)
            );
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        // 🔹 Fetch collector
        Collector collector = collectorRepository.findByCollectorIdIgnoreCase(collectorId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Collector not found")
                );

        // 🔹 Generate JWT using UserDetails
        UserDetailImpl userDetails = new UserDetailImpl(collector);

        return jwtUtility.generateToken(userDetails.getUsername());
    }

    // 🔐 RANDOM PASSWORD GENERATOR
    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current().nextInt(chars.length());
            password.append(chars.charAt(index));
        }

        return password.toString();
    }
}


//package com.SmartGarbageCollection.GarbageCollection.Service;
//import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorCreateDTO;
//import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorResponseDTO;
//import com.SmartGarbageCollection.GarbageCollection.Entity.Admin;
//import com.SmartGarbageCollection.GarbageCollection.Entity.Collector;
//import com.SmartGarbageCollection.GarbageCollection.Entity.Role;
//import com.SmartGarbageCollection.GarbageCollection.Repository.AdminRepository;
//import com.SmartGarbageCollection.GarbageCollection.Repository.CollectorRepository;
//import com.SmartGarbageCollection.GarbageCollection.Utilis.JwtUtility;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class CollectorServiceImpl implements CollectorService {
//
//    private final CollectorRepository collectorRepository;
//    private final AdminRepository adminRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtility jwtUtility;
//
//    @Override
//    public CollectorResponseDTO createCollector(CollectorCreateDTO dto, String adminEmail) {
//
//        // 🔹 1. Check phone uniqueness
//        if (collectorRepository.existsByPhone(dto.getPhone())) {
//            throw new RuntimeException("Collector already exists with this phone");
//        }
//
//        // 🔹 2. Generate unique collectorId
//        String collectorId;
//        do {
//            int randomNum = (int) (Math.random() * 10000); // 0–9999
//            collectorId = String.format("COLL-%04d", randomNum);
//        } while (collectorRepository.findByCollectorId(collectorId).isPresent());
//
//        // 🔹 3. Generate 5-char password
//        String rawPassword = generateRandomPassword(5);
//
//        // 🔹 4. Get admin
//        Admin admin = adminRepository.findByEmail(adminEmail)
//                .orElseThrow(() -> new RuntimeException("Admin not found"));
//
//        // 🔹 5. Create collector entity
//        Collector collector = Collector.builder()
//                .collectorId(collectorId)
//                .name(dto.getName())
//                .phone(dto.getPhone())
//                .password(passwordEncoder.encode(rawPassword))
//                .role(Role.ROLE_COLLECTOR)
//                .createdAt(LocalDateTime.now())
//                .createdBy(admin.getId())
//                .build();
//
//        collectorRepository.save(collector);
//
//        // 🔹 6. Return credentials
//        return new CollectorResponseDTO(collectorId, rawPassword);
//    }
//
//    // 🔐 Password generator
//    private String generateRandomPassword(int length) {
//        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        StringBuilder password = new StringBuilder();
//
//        for (int i = 0; i < length; i++) {
//            int index = (int) (Math.random() * chars.length());
//            password.append(chars.charAt(index));
//        }
//
//        return password.toString();
//    }
//    @Override
//    public String login(String collectorId, String password) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        collectorId,
//                        password
//                )
//        );
//
//        return jwtUtility.generateToken(collectorId);
//    }
//}