package com.SmartGarbageCollection.GarbageCollection.Controller;

import com.SmartGarbageCollection.GarbageCollection.DTO.PickupRequestDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.PickupStatusUpdateDTO;
import com.SmartGarbageCollection.GarbageCollection.Entity.Pickup;
import com.SmartGarbageCollection.GarbageCollection.Service.PickupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pickups")
@RequiredArgsConstructor
public class PickupController {

    private final PickupService pickupService;

    // 🔹 CREATE PICKUP
    @PostMapping
    public ResponseEntity<String> requestPickup(
            @Valid @RequestBody PickupRequestDTO request,
            Authentication authentication
    ) {

        String userName = authentication.getName();

        Pickup pickup = pickupService.createPickup(
                userName,
                request.getRequestDate(),
                request.getLatitude(),
                request.getLongitude()
        );

        return ResponseEntity.ok(pickup.getId());
    }

    // 🔹 UPDATE STATUS
    @PutMapping("/status")
    public ResponseEntity<String> updateStatus(
            @Valid @RequestBody PickupStatusUpdateDTO request,
            Authentication authentication
    ) {

        String userName = authentication.getName();

        pickupService.updateStatus(
                request.getPickupId(),
                request.getStatus(),
                userName,
                request.getPoints()
        );

        return ResponseEntity.ok("Status updated successfully");
    }

    // 🔹 GET ACTIVE PICKUP
    @GetMapping("/active")
    public ResponseEntity<Pickup> getActivePickup(Authentication authentication) {

        String userName = authentication.getName();

        Pickup pickup = pickupService.getActivePickup(userName);

        return ResponseEntity.ok(pickup);
    }
}




//package com.SmartGarbageCollection.GarbageCollection.Controller;
//
//import com.SmartGarbageCollection.GarbageCollection.DTO.PickupRequestDTO;
//import com.SmartGarbageCollection.GarbageCollection.DTO.PickupStatusUpdateDTO;
//import com.SmartGarbageCollection.GarbageCollection.Entity.Pickup;
//import com.SmartGarbageCollection.GarbageCollection.Service.PickupService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/pickup")
//public class PickupController {
//
//    @Autowired
//    private PickupService pickupService;
//
//    @PostMapping("/request")
//    public ResponseEntity<String> requestPickup(@RequestBody PickupRequestDTO request) {
//
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        Pickup pickup = pickupService.createPickup(
//                userName,
//                request.getRequestDate(),
//                request.getLatitude(),
//                request.getLongitude()
//        );
//
////        return ResponseEntity.ok(pickup.getId());
//        return ResponseEntity.ok(String.valueOf(pickup.getId()));
//    }
//    @PutMapping("/update-status")
//    public ResponseEntity<Void> updateStatus(@RequestBody PickupStatusUpdateDTO request)
//    {
//
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        pickupService.updateStatus(
//                request.getPickupId(),
//                request.getStatus(),
//                userName, request.getPoints()
//        );
//
//        return ResponseEntity.ok().build();
//    }
//    @GetMapping("/active")
//    public ResponseEntity<?> getActivePickup() {
//
//        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        Pickup pickup = pickupService.getActivePickup(userName);
//
//        return ResponseEntity.ok(pickup);
//    }
//    @GetMapping("/test")
//    public String test() {
//        return "WORKING";
//    }
//}