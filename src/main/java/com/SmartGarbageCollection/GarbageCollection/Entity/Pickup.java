package com.SmartGarbageCollection.GarbageCollection.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pickup")
@Getter
@Setter
@NoArgsConstructor
public class Pickup {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private String id = java.util.UUID.randomUUID().toString();

    // Foreign key → User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate requestDate;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private PickupStatus status; // Collected/NOT_Collected etc.
}