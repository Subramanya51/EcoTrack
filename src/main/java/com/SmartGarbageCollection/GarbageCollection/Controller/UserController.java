package com.SmartGarbageCollection.GarbageCollection.Controller;

import com.SmartGarbageCollection.GarbageCollection.DTO.*;
import com.SmartGarbageCollection.GarbageCollection.Entity.User;
import com.SmartGarbageCollection.GarbageCollection.Service.UserService;
import com.SmartGarbageCollection.GarbageCollection.Utilis.JwtUtility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;
    private final UserService userService;

    // 🔐 LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO dto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUserName(),
                        dto.getPassword()
                )
        );

        // ✅ SAFE (no casting)
        String username = authentication.getName();

        String token = jwtUtility.generateToken(username);

        return ResponseEntity.ok(token);
    }
    // 🔹 UPDATE PROFILE
    @PutMapping("/profile-edit")
    public ResponseEntity<String> updateProfile(@Valid @RequestBody UpdateProfileDTO dto) {

        String userName = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        userService.updateProfile(userName, dto);

        return ResponseEntity.ok("Profile updated successfully");
    }

    // 🔐 CHANGE PASSWORD
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordDTO dto) {

        String userName = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        userService.changePassword(userName, dto);

        return ResponseEntity.ok("Password updated successfully");
    }

    // 🔹 GET PROFILE
    @GetMapping("/profile")
    public ResponseEntity<ProfileDTO> getProfile() {

        String userName = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return ResponseEntity.ok(userService.getProfile(userName));
    }
}

































//package com.SmartGarbageCollection.GarbageCollection.Controller;
//
//import com.SmartGarbageCollection.GarbageCollection.DTO.ChangePasswordDTO;
//import com.SmartGarbageCollection.GarbageCollection.DTO.UpdateProfileDTO;
//import com.SmartGarbageCollection.GarbageCollection.Entity.User;
//import com.SmartGarbageCollection.GarbageCollection.Service.UserService;
//import com.SmartGarbageCollection.GarbageCollection.Utilis.JwtUtility;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping({"/auth"})
//public class UserController
//{
//    @Autowired
//    AuthenticationManager authenticationManager;
//    @Autowired
//    JwtUtility jwtUtility;
//    @Autowired
//    UserService userService;
//    @PostMapping({"/login"})
//    public String UserLogin(@RequestBody User user)
//    {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
//            );
//            return jwtUtility.generateToken(user.getUserName());
//        } catch (Exception e) {
//            throw e;
//        }
//
//    }
//    @PutMapping("/user/profile/update")
//    public ResponseEntity<User> updateProfile(@RequestBody UpdateProfileDTO dto) {
//
//        String userName = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName();
//
//        User updatedUser = userService.updateProfile(userName, dto);
//
//        return ResponseEntity.ok(updatedUser);
//    }
//    @PutMapping("/change-password")
//    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO dto) {
//
//        String userName = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName();
//
//        userService.changePassword(userName, dto);
//
//        return ResponseEntity.ok().build();
//    }
//}
