//package com.SmartGarbageCollection.GarbageCollection.Service;
//
//import com.SmartGarbageCollection.GarbageCollection.Entity.User;
//import com.SmartGarbageCollection.GarbageCollection.Entity.UserDetailImpl;
//import com.SmartGarbageCollection.GarbageCollection.Repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceDetailImpl implements UserDetailsService
//{
//    @Autowired
//    private UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//        return new UserDetailImpl(user);
//    }
//}
package com.SmartGarbageCollection.GarbageCollection.Service;

import com.SmartGarbageCollection.GarbageCollection.Entity.Admin;
import com.SmartGarbageCollection.GarbageCollection.Entity.Collector;
import com.SmartGarbageCollection.GarbageCollection.Entity.User;
import com.SmartGarbageCollection.GarbageCollection.Entity.UserDetailImpl;
import com.SmartGarbageCollection.GarbageCollection.Repository.AdminRepository;
import com.SmartGarbageCollection.GarbageCollection.Repository.CollectorRepository;
import com.SmartGarbageCollection.GarbageCollection.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetailImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CollectorRepository collectorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 🔹 1. Check USERS (resident)
        User user = userRepository.findByUserName(username).orElse(null);

        if (user != null) {
            return new UserDetailImpl(user);
        }

        // 🔹 2. Check ADMINS
        Admin admin = adminRepository.findByEmail(username).orElse(null);

        if (admin != null) {
            return new UserDetailImpl(admin);
        }
        // 🔥 ADD THIS (COLLECTOR)
        Collector collector = collectorRepository.findByCollectorId(username).orElse(null);
        if (collector != null) {
            return new UserDetailImpl(collector);
        }

        // 🔹 3. Not found anywhere
        throw new UsernameNotFoundException("User/Admin/Collector not found with: " + username);
    }
}