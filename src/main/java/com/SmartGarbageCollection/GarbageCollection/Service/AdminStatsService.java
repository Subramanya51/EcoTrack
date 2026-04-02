package com.SmartGarbageCollection.GarbageCollection.Service;

import com.SmartGarbageCollection.GarbageCollection.DTO.DailyStatsDTO;

public interface AdminStatsService {

    // 🔹 Get today's dashboard stats (admin dashboard)
    DailyStatsDTO getTodayStats();
}

//package com.SmartGarbageCollection.GarbageCollection.Service;
//
//import com.SmartGarbageCollection.GarbageCollection.DTO.DailyStatsDTO;
//
//public interface AdminStatsService {
//
//    // 🔹 Get today's dashboard stats
//    DailyStatsDTO getTodayStats();
//}