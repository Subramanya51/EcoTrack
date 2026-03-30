package com.SmartGarbageCollection.GarbageCollection.Service;

import com.SmartGarbageCollection.GarbageCollection.DTO.ChangePasswordDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.ProfileDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.UpdateProfileDTO;
import com.SmartGarbageCollection.GarbageCollection.Entity.User;
import com.SmartGarbageCollection.GarbageCollection.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//    public User registerUser(User user)
//    {
//        userRepository.save(user);
//        return user;
//    }
//
//}
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //Register User
    public User registerUser(User user)
    {
        if (userRepository.findByUserName(user.getUserName()).isPresent() ||
                userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    // ✅ EDIT PROFILE
    public User updateProfile(String currentUserName, UpdateProfileDTO dto)
    {

        User user = userRepository.findByUserName(currentUserName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔒 check duplicate username
        if (dto.getUserName() != null &&
                !dto.getUserName().equals(user.getUserName()) &&
                userRepository.findByUserName(dto.getUserName()).isPresent()) {

            throw new RuntimeException("Username already exists");
        }

        if (dto.getUserName() != null) {
            user.setUserName(dto.getUserName());
        }

        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }

        if (dto.getAddress() != null) {
            user.setAddress(dto.getAddress());
        }

        return userRepository.save(user);
    }
    //Edit Password
    public void changePassword(String userName, ChangePasswordDTO dto) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔐 verify old password
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid old password");
        }

        // 🔐 encode new password
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));

        userRepository.save(user);
    }
    public ProfileDTO getProfile(String userName) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProfileDTO dto = new ProfileDTO();

        dto.setUserName(user.getUserName());
        dto.setEcoPoints(user.getEcoPoints());
        dto.setTotalPickups(user.getTotalPickups());
        dto.setLastPickupDate(user.getLastPickupDate());

        return dto;
    }
}