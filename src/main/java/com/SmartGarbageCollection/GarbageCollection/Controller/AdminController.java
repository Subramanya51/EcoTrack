package com.SmartGarbageCollection.GarbageCollection.Controller;

import com.SmartGarbageCollection.GarbageCollection.DTO.*;
import com.SmartGarbageCollection.GarbageCollection.Service.AdminService;
import com.SmartGarbageCollection.GarbageCollection.Service.AdminStatsService;
import com.SmartGarbageCollection.GarbageCollection.Service.CollectorService;
import com.SmartGarbageCollection.GarbageCollection.Utilis.JwtUtility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;
    private final AdminService adminService;
    private final CollectorService collectorService;
    private final AdminStatsService adminStatsService;

    // 🔹 REGISTER ADMIN
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody AdminRegisterDTO dto) {

        String response = adminService.registerAdmin(dto);

        return ResponseEntity.ok(response);
    }

    // 🔐 LOGIN ADMIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AdminLoginDTO dto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );

        // ✅ SAFE and CORRECT
        String username = authentication.getName();

        String token = jwtUtility.generateToken(username);

        return ResponseEntity.ok(token);
    }
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@Valid @RequestBody AdminLoginDTO dto) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        dto.getEmail(),
//                        dto.getPassword()
//                )
//        );
//
//        UserDetails userDetails = (UserDetails) authenticationManager
//                .authenticate(
//                        new UsernamePasswordAuthenticationToken(
//                                dto.getEmail(),
//                                dto.getPassword()
//                        )
//                ).getPrincipal();
//
//        String token = jwtUtility.generateToken(userName);
//
//        return ResponseEntity.ok(token);
//    }

    // 🔥 CREATE COLLECTOR (ADMIN ONLY)
    @PostMapping("/collectors")
    public ResponseEntity<CollectorResponseDTO> createCollector(
            @Valid @RequestBody CollectorCreateDTO dto,
            Authentication authentication
    ) {

        String adminEmail = authentication.getName();

        CollectorResponseDTO response =
                collectorService.createCollector(dto, adminEmail);

        return ResponseEntity.ok(response);
    }

    // 📊 GET TODAY STATS
    @GetMapping("/stats/today")
    public ResponseEntity<DailyStatsDTO> getTodayStats() {

        DailyStatsDTO stats = adminStatsService.getTodayStats();

        return ResponseEntity.ok(stats);
    }
    @GetMapping("/collector-data")
    public ResponseEntity<List<CollectorListDTO>> getAllCollectors() {

        return ResponseEntity.ok(collectorService.getAllCollectors());
    }
}


////package com.SmartGarbageCollection.GarbageCollection.Controller;
////import com.SmartGarbageCollection.GarbageCollection.DTO.AdminLoginDTO;
////import com.SmartGarbageCollection.GarbageCollection.DTO.AdminRegisterDTO;
////import com.SmartGarbageCollection.GarbageCollection.Service.AdminService;
////import lombok.RequiredArgsConstructor;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/admin")
////@RequiredArgsConstructor
////public class AdminController {
////
////    private final AdminService adminService;
////
////    @PostMapping("/register")
////    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegisterDTO request) {
////
////        String response = adminService.registerAdmin(
////                request.getName(),
////                request.getEmail(),
////                request.getPassword()
////        );
////
////        return ResponseEntity.ok(response);
////    }
////
////    @PostMapping("/login")
////    public ResponseEntity<String> loginAdmin(@RequestBody AdminLoginDTO request) {
////
////        String response = adminService.loginAdmin(
////                request.getEmail(),
////                request.getPassword()
////        );
////
////        return ResponseEntity.ok(response);
////    }
////}
//package com.SmartGarbageCollection.GarbageCollection.Controller;
//
//import com.SmartGarbageCollection.GarbageCollection.DTO.*;
//import com.SmartGarbageCollection.GarbageCollection.Service.AdminService;
//import com.SmartGarbageCollection.GarbageCollection.Service.AdminStatsService;
//import com.SmartGarbageCollection.GarbageCollection.Service.CollectorService;
//import com.SmartGarbageCollection.GarbageCollection.Utilis.JwtUtility;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/admin/auth")
//public class AdminController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtility jwtUtility;
//
//    @Autowired
//    private AdminService adminService;
//    @Autowired
//    private  CollectorService collectorService;
//    @Autowired
//    private AdminStatsService adminStatsService;
//
//    // 🔹 REGISTER
//    @PostMapping("/register")
//    public String registerAdmin(@RequestBody AdminRegisterDTO dto) {
//        return adminService.registerAdmin(dto);
//    }
//
//    // 🔹 LOGIN (JWT FLOW - SAME AS USER)
//    @PostMapping("/login")
//    public String adminLogin(@RequestBody AdminLoginDTO dto) {
//
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            dto.getEmail(),
//                            dto.getPassword()
//                    )
//            );
//
//            return jwtUtility.generateToken(dto.getEmail());
//
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//    // 🔥 CREATE COLLECTOR (ADMIN ONLY)
//    @PostMapping("/collector/create")
//    public ResponseEntity<CollectorResponseDTO> createCollector(
//            @RequestBody CollectorCreateDTO dto) {
//
//        String adminEmail = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName();
//
//        CollectorResponseDTO response =
//                collectorService.createCollector(dto, adminEmail);
//
//        return ResponseEntity.ok(response);
//    }
//    @GetMapping("/stats/today")
//    public ResponseEntity<DailyStatsDTO> getTodayStats() {
//
//        DailyStatsDTO stats = adminStatsService.getTodayStats();
//
//        return ResponseEntity.ok(stats);
//    }
//}