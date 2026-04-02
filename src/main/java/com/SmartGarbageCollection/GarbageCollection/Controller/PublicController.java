package com.SmartGarbageCollection.GarbageCollection.Controller;

import com.SmartGarbageCollection.GarbageCollection.DTO.UserRegisterDTO;
import com.SmartGarbageCollection.GarbageCollection.Entity.Role;
import com.SmartGarbageCollection.GarbageCollection.Entity.User;
import com.SmartGarbageCollection.GarbageCollection.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {

    private final UserService userService;

    // 🔹 REGISTER USER
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegisterDTO dto) {

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAddress(dto.getAddress());
        user.setRole(Role.ROLE_RESIDENT);

        userService.registerUser(user);

        return ResponseEntity.ok("User registered successfully");
    }
}


//package com.SmartGarbageCollection.GarbageCollection.Controller;
//
//import com.SmartGarbageCollection.GarbageCollection.Entity.User;
//import com.SmartGarbageCollection.GarbageCollection.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping({"/public"})
//public class PublicController
//{
//    @Autowired
//    private UserService userService;
//
////    @PostMapping("/register")
////    public String registerUser(@RequestBody User user)
////    {        // Encode the password before saving
////        user.setPassword(passwordEncoder.encode(user.getPassword()));
////        userService.registerUser(user);
////        return "User registered successfully";
////    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        userService.registerUser(user);
//        return ResponseEntity.ok("User registered successfully");
//    }
//}
//
