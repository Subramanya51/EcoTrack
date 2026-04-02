package com.SmartGarbageCollection.GarbageCollection.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PickupRequestDTO {

    @NotNull(message = "Request date is required")
    private LocalDate requestDate;

    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Latitude must be <= 90")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Longitude must be <= 180")
    private Double longitude;
}



//package com.SmartGarbageCollection.GarbageCollection.DTO;
//
//import lombok.Data;
//
//import java.time.LocalDate;
//
//@Data
//public class PickupRequestDTO {
//    private LocalDate requestDate;
//    private Double latitude;
//    private Double longitude;
//}