package com.SmartGarbageCollection.GarbageCollection.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_username", columnList = "userName")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer ecoPoints;

    @Column(nullable = false)
    private Integer totalPickups;

    @Column
    private String address;

    @Column
    private LocalDate lastPickupDate;

    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = Role.RESIDENT;
        }
        if (this.ecoPoints == null) {
            this.ecoPoints = 0;
        }
        if (this.totalPickups == null) {
            this.totalPickups = 0;
        }
    }
}


////package com.SmartGarbageCollection.GarbageCollection.Entity;
////
////
////import jakarta.persistence.*;
////import lombok.Getter;
////import lombok.NoArgsConstructor;
////import lombok.Setter;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
////
////import java.util.Collection;
////import java.util.Collections;
////
////@Entity
////@Table(name = "users")
////@Getter
////@Setter
////@NoArgsConstructor
////public class User
////{
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;
////
////    @Column(unique = true, nullable = false)
////    private String userName;
////
////    @Enumerated(EnumType.STRING)
////    @Column(nullable = false)
////    private Role role;
////
////    @Column(nullable = false)
////    private String password;
////
////
////}
//package com.SmartGarbageCollection.GarbageCollection.Entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDate;
//import java.util.UUID;
//
//@Entity
//@Table(name = "users")
//@Getter
//@Setter
//@NoArgsConstructor
//public class User {
//
//    @Id
//    @Column(nullable = false, updatable = false, unique = true)
//    private String id = UUID.randomUUID().toString();
//
//    @Column(unique = true, nullable = false)
//    private String userName;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
//
//    @Column(nullable = false)
//    private String phoneNumber;
//
//    @Column(nullable = false)
//    private Integer ecoPoints = 0;
//
//    @Column(nullable = false)
//    private Integer totalPickups = 0;
//
//    @Column
//    private String address;
//
//    @Column
//    private LocalDate lastPickupDate;
//}
