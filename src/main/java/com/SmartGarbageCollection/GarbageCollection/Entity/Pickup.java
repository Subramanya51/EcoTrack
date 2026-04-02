package com.SmartGarbageCollection.GarbageCollection.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "pickups",
        indexes = {
                @Index(name = "idx_pickup_user", columnList = "user_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Pickup {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate requestDate;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PickupStatus status;

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = PickupStatus.REQUESTED;
        }
    }
}

//package com.SmartGarbageCollection.GarbageCollection.Entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "pickup")
//@Getter
//@Setter
//@NoArgsConstructor
//public class Pickup {
//
//    @Id
//    @Column(nullable = false, updatable = false, unique = true)
//    private String id = java.util.UUID.randomUUID().toString();
//
//    // Foreign key → User
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @Column(nullable = false)
//    private LocalDate requestDate;
//
//    @Column(nullable = false)
//    private Double latitude;
//
//    @Column(nullable = false)
//    private Double longitude;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = true)
//    private PickupStatus status; // Collected/NOT_Collected etc.
//}