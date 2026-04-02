package com.SmartGarbageCollection.GarbageCollection.Controller;

import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorLoginDTO;
import com.SmartGarbageCollection.GarbageCollection.Service.CollectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collectors")
@RequiredArgsConstructor
public class CollectorController {

    private final CollectorService collectorService;

    // 🔐 LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody CollectorLoginDTO dto) {

        String token = collectorService.login(
                dto.getCollectorId(),
                dto.getPassword()
        );

        return ResponseEntity.ok(token);
    }
}



//package com.SmartGarbageCollection.GarbageCollection.Controller;
//
//import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorLoginDTO;
//import com.SmartGarbageCollection.GarbageCollection.Service.CollectorService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/collector/auth")
//@RequiredArgsConstructor
//public class CollectorController {
//
//    private final CollectorService collectorService;
//
//    @PostMapping("/login")
//    public String login(@RequestBody CollectorLoginDTO dto) {
//
//        return collectorService.login(
//                dto.getCollectorId(),
//                dto.getPassword()
//        );
//    }
//}