package com.SmartGarbageCollection.GarbageCollection.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailImpl implements UserDetails {

    private final String username;
    private final String password;
    private final Role role;

    // 🔹 User
    public UserDetailImpl(User user) {
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    // 🔹 Admin
    public UserDetailImpl(Admin admin) {
        this.username = admin.getEmail();
        this.password = admin.getPassword();
        this.role = admin.getRole();
    }

    // 🔹 Collector
    public UserDetailImpl(Collector collector) {
        this.username = collector.getCollectorId();
        this.password = collector.getPassword();
        this.role = collector.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority(role.name()) // ✅ FIXED
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}





//////package com.SmartGarbageCollection.GarbageCollection.Entity;
//////
//////import org.springframework.security.core.GrantedAuthority;
//////import org.springframework.security.core.authority.SimpleGrantedAuthority;
//////import org.springframework.security.core.userdetails.UserDetails;
//////
//////import java.util.Collection;
//////import java.util.Collections;
//////
//////public class UserDetailImpl implements UserDetails {
//////
//////    private final User user;
//////
//////    public UserDetailImpl(User user) {
//////        this.user = user;
//////    }
//////
//////    @Override
//////    public Collection<GrantedAuthority> getAuthorities() {
//////        return Collections.singletonList(
//////                new SimpleGrantedAuthority(user.getRole().name())
//////        );
//////    }
//////
//////    @Override
//////    public String getPassword() {
//////        return user.getPassword();
//////    }
//////
//////    @Override
//////    public String getUsername() {
//////        return user.getUserName();
//////    }
//////
//////    @Override
//////    public boolean isAccountNonExpired() {
//////        return true; // Customize logic if needed
//////    }
//////
//////    @Override
//////    public boolean isAccountNonLocked() {
//////        return true; // Customize logic if needed
//////    }
//////
//////    @Override
//////    public boolean isCredentialsNonExpired() {
//////        return true; // Customize logic if needed
//////    }
//////
//////    @Override
//////    public boolean isEnabled() {
//////        return true; // Customize logic if needed
//////    }
//////}
////package com.SmartGarbageCollection.GarbageCollection.Entity;
////
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
////
////import java.util.Collection;
////import java.util.Collections;
////
////public class UserDetailImpl implements UserDetails {
////
////    private String username;
////    private String password;
////    private String role;
////
////    // 🔹 Constructor for User
////    public UserDetailImpl(User user) {
////        this.username = user.getUserName();
////        this.password = user.getPassword();
////        this.role = user.getRole().name();
////    }
////
////    // 🔹 Constructor for Admin
////    public UserDetailImpl(Admin admin) {
////        this.username = admin.getEmail();
////        this.password = admin.getPassword();
////        this.role = admin.getRole().name();
////    }
////
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        return Collections.singletonList(
////                new SimpleGrantedAuthority("ROLE_" + role)
////        );
////    }
////
////    @Override
////    public String getPassword() {
////        return password;
////    }
////
////    @Override
////    public String getUsername() {
////        return username;
////    }
////
////    @Override
////    public boolean isAccountNonExpired() {
////        return true;
////    }
////
////    @Override
////    public boolean isAccountNonLocked() {
////        return true;
////    }
////
////    @Override
////    public boolean isCredentialsNonExpired() {
////        return true;
////    }
////
////    @Override
////    public boolean isEnabled() {
////        return true;
////    }
////}
//package com.SmartGarbageCollection.GarbageCollection.Entity;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class UserDetailImpl implements UserDetails {
//
//    private String username;
//    private String password;
//    private String role;
//
//    // 🔹 Constructor for User
//    public UserDetailImpl(User user) {
//        this.username = user.getUserName();
//        this.password = user.getPassword();
//        this.role = user.getRole().name();
//    }
//
//    // 🔹 Constructor for Admin
//    public UserDetailImpl(Admin admin) {
//        this.username = admin.getEmail();
//        this.password = admin.getPassword();
//        this.role = admin.getRole().name();
//    }
//
//    // 🔥 ADD THIS (Collector support)
//    public UserDetailImpl(Collector collector) {
//        this.username = collector.getCollectorId();
//        this.password = collector.getPassword();
//        this.role = collector.getRole().name();
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(
//                new SimpleGrantedAuthority("ROLE_" + role)
//        );
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}