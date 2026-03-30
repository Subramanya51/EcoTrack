package com.SmartGarbageCollection.GarbageCollection.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollectorResponseDTO {
    private String collectorId;
    private String password; // raw password (only returned once)
}