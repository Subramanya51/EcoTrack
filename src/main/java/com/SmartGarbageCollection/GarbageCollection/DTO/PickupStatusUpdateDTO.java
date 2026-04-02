package com.SmartGarbageCollection.GarbageCollection.DTO;

import com.SmartGarbageCollection.GarbageCollection.Entity.PickupStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickupStatusUpdateDTO {

    @NotBlank(message = "Pickup ID is required")
    private String pickupId;

    @NotNull(message = "Status is required")
    private PickupStatus status;  // ✅ ENUM instead of String

    @Min(value = 0, message = "Points cannot be negative")
    private Integer points;
}


//package com.SmartGarbageCollection.GarbageCollection.DTO;
//
//import lombok.Data;
//
//@Data
//public class PickupStatusUpdateDTO {
//
//    private String pickupId;
//    private String status;
//    private Integer points;
//
//}