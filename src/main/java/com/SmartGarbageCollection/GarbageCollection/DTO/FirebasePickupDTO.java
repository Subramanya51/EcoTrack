package com.SmartGarbageCollection.GarbageCollection.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FirebasePickupDTO {

    private final String pickupId;
    private final String userId;
    private final String date;
    private final Double latitude;
    private final Double longitude;
    private final String status;
}


//package com.SmartGarbageCollection.GarbageCollection.DTO;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@AllArgsConstructor
//public class FirebasePickupDTO {
//
//    private String pickupId;
//    private String userId;
//    private String date;
//    private Double latitude;
//    private Double longitude;
//    private String status;
//}