package com.SmartGarbageCollection.GarbageCollection.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PickupRequestDTO {
    private LocalDate requestDate;
    private Double latitude;
    private Double longitude;
}