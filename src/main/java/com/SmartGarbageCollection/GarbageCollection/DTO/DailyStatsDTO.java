package com.SmartGarbageCollection.GarbageCollection.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailyStatsDTO {

    private long total;
    private long collected;
    private long notCollected;
    private long active;
}