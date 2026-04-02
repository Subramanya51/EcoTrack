package com.SmartGarbageCollection.GarbageCollection.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<Map<String, Object>> healthCheck() {

        return ResponseEntity.ok(
                Map.of(
                        "status", "UP",
                        "timestamp", Instant.now().toString()
                )
        );
    }
}



//package com.SmartGarbageCollection.GarbageCollection.Controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class HealthCheckController {
//    @GetMapping("/health")
//    public String healthCheck() {
//        return "OK";
//    }
//}
