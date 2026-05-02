package com.SmartGarbageCollection.GarbageCollection.Service;

import  com.SmartGarbageCollection.GarbageCollection.DTO.ChangePasswordDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.ProfileDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.UpdateProfileDTO;
import com.SmartGarbageCollection.GarbageCollection.Entity.User;
import com.SmartGarbageCollection.GarbageCollection.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 🔹 REGISTER USER
    @Transactional
    public User registerUser(User user) {

        if (userRepository.existsByUserName(user.getUserName())) {
            throw new IllegalStateException("Username already exists");
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new IllegalStateException("Phone number already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // 🔹 UPDATE PROFILE
    @Transactional
    public User updateProfile(String currentUserName, UpdateProfileDTO dto) {

        User user = userRepository.findByUserNameIgnoreCase(currentUserName)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        // 🔒 Username update check
        if (dto.getUserName() != null &&
                !dto.getUserName().equalsIgnoreCase(user.getUserName()) &&
                userRepository.existsByUserName(dto.getUserName())) {

            throw new IllegalStateException("Username already exists");
        }

        // 🔒 Phone update check
        if (dto.getPhoneNumber() != null &&
                !dto.getPhoneNumber().equals(user.getPhoneNumber()) &&
                userRepository.existsByPhoneNumber(dto.getPhoneNumber())) {

            throw new IllegalStateException("Phone number already exists");
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

    // 🔹 CHANGE PASSWORD
    @Transactional
    public void changePassword(String userName, ChangePasswordDTO dto) {

        User user = userRepository.findByUserNameIgnoreCase(userName)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new IllegalStateException("Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));

        userRepository.save(user);
    }

    // 🔹 GET PROFILE
    public ProfileDTO getProfile(String userName) {

        User user = userRepository.findByUserNameIgnoreCase(userName)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        ProfileDTO dto = new ProfileDTO();

        dto.setUserName(user.getUserName());
        dto.setEcoPoints(user.getEcoPoints());
        dto.setTotalPickups(user.getTotalPickups());
        dto.setLastPickupDate(user.getLastPickupDate());

        return dto;
    }
}






















//package com.SmartGarbageCollection.GarbageCollection.Service;
//
//import com.SmartGarbageCollection.GarbageCollection.DTO.ChangePasswordDTO;
//import com.SmartGarbageCollection.GarbageCollection.DTO.ProfileDTO;
//import com.SmartGarbageCollection.GarbageCollection.DTO.UpdateProfileDTO;
//import com.SmartGarbageCollection.GarbageCollection.Entity.User;
//import com.SmartGarbageCollection.GarbageCollection.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
////
////@Service
////public class UserService {
////    @Autowired
////    private UserRepository userRepository;
////    public User registerUser(User user)
////    {
////        userRepository.save(user);
////        return user;
////    }
////
////}
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    //Register User
//    public User registerUser(User user)
//    {
//        if (userRepository.findByUserName(user.getUserName()).isPresent() ||
//                userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
//            throw new RuntimeException("User already exists");
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//    // ✅ EDIT PROFILE
//    public User updateProfile(String currentUserName, UpdateProfileDTO dto)
//    {
//
//        User user = userRepository.findByUserName(currentUserName)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // 🔒 check duplicate username
//        if (dto.getUserName() != null &&
//                !dto.getUserName().equals(user.getUserName()) &&
//                userRepository.findByUserName(dto.getUserName()).isPresent()) {
//
//            throw new RuntimeException("Username already exists");
//        }
//
//        if (dto.getUserName() != null) {
//            user.setUserName(dto.getUserName());
//        }
//
//        if (dto.getPhoneNumber() != null) {
//            user.setPhoneNumber(dto.getPhoneNumber());
//        }
//
//        if (dto.getAddress() != null) {
//            user.setAddress(dto.getAddress());
//        }
//
//        return userRepository.save(user);
//    }
//    //Edit Password
//    public void changePassword(String userName, ChangePasswordDTO dto) {
//
//        User user = userRepository.findByUserName(userName)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // 🔐 verify old password
//        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid old password");
//        }
//
//        // 🔐 encode new password
//        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
//
//        userRepository.save(user);
//    }
//    public ProfileDTO getProfile(String userName) {
//
//        User user = userRepository.findByUserName(userName)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        ProfileDTO dto = new ProfileDTO();
//
//        dto.setUserName(user.getUserName());
//        dto.setEcoPoints(user.getEcoPoints());
//        dto.setTotalPickups(user.getTotalPickups());
//        dto.setLastPickupDate(user.getLastPickupDate());
//
//        return dto;
//    }
//}