package com.SmartGarbageCollection.GarbageCollection.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollectorListDTO {

    private String collectorId;
    private String name;
    private String phone;
}