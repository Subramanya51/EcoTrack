package com.SmartGarbageCollection.GarbageCollection.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "collectors",
        indexes = {
                @Index(name = "idx_collector_business_id", columnList = "collectorId"),
                @Index(name = "idx_collector_phone", columnList = "phone")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Collector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String collectorId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private LocalDateTime createdAt;

    private Long createdBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        if (this.role == null) {
            this.role = Role.COLLECTOR;
        }
    }
}

//package com.SmartGarbageCollection.GarbageCollection.Entity;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "collectors")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Collector {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;   // Internal DB ID (auto unique)
//
//    @Column(unique = true, nullable = false)
//    private String collectorId;   // COLL-XXXX (business ID)
//
//    private String name;
//
//    @Column(unique = true, nullable = false)
//    private String phone;
//
//    private String password;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    private LocalDateTime createdAt;
//
//    private Long createdBy; // Admin ID (optional but useful)
//}