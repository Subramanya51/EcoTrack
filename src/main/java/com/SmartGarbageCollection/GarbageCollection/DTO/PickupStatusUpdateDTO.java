package com.SmartGarbageCollection.GarbageCollection.DTO;

import lombok.Data;

@Data
public class PickupStatusUpdateDTO {

    private String pickupId;
    private String status;
    private Integer points;

}