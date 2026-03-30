package com.SmartGarbageCollection.GarbageCollection.Controller;

import com.SmartGarbageCollection.GarbageCollection.DTO.ChangePasswordDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.UpdateProfileDTO;
import com.SmartGarbageCollection.GarbageCollection.Entity.User;
import com.SmartGarbageCollection.GarbageCollection.Service.UserService;
import com.SmartGarbageCollection.GarbageCollection.Utilis.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/auth"})
public class UserController
{
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtility jwtUtility;
    @Autowired
    UserService userService;
    @PostMapping({"/login"})
    public String UserLogin(@RequestBody User user)
    {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );
            return jwtUtility.generateToken(user.getUserName());
        } catch (Exception e) {
            throw e;
        }

    }
    @PutMapping("/user/profile/update")
    public ResponseEntity<User> updateProfile(@RequestBody UpdateProfileDTO dto) {

        String userName = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User updatedUser = userService.updateProfile(userName, dto);

        return ResponseEntity.ok(updatedUser);
    }
    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO dto) {

        String userName = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        userService.changePassword(userName, dto);

        return ResponseEntity.ok().build();
    }
}
