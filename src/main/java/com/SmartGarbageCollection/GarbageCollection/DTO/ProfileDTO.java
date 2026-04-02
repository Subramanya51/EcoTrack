package com.SmartGarbageCollection.GarbageCollection.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProfileDTO {

    private String userName;
    private Integer ecoPoints;
    private Integer totalPickups;
    private LocalDate lastPickupDate;
}


//package com.SmartGarbageCollection.GarbageCollection.DTO;
//
//import lombok.Data;
//import java.time.LocalDate;
//
//@Data
//public class ProfileDTO {
//
//    private String userName;
//    private Integer ecoPoints;
//    private Integer totalPickups;
//    private LocalDate lastPickupDate;
//}