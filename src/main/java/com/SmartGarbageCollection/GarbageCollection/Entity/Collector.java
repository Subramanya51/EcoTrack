package com.SmartGarbageCollection.GarbageCollection.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "collectors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Collector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Internal DB ID (auto unique)

    @Column(unique = true, nullable = false)
    private String collectorId;   // COLL-XXXX (business ID)

    private String name;

    @Column(unique = true, nullable = false)
    private String phone;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

    private Long createdBy; // Admin ID (optional but useful)
}