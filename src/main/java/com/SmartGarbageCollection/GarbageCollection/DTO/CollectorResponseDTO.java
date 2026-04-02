package com.SmartGarbageCollection.GarbageCollection.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CollectorResponseDTO {

    private final String collectorId;

    // ⚠️ One-time password (shown only during creation)
    private final String password;
}



//package com.SmartGarbageCollection.GarbageCollection.DTO;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class CollectorResponseDTO {
//    private String collectorId;
//    private String password; // raw password (only returned once)
//}