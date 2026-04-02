package com.SmartGarbageCollection.GarbageCollection.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DailyStatsDTO {

    private final long total;
    private final long completed;
    private final long failed;
    private final long active;
}


//package com.SmartGarbageCollection.GarbageCollection.DTO;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class DailyStatsDTO {
//
//    private long total;
//    private long collected;
//    private long notCollected;
//    private long active;
//}