package com.SmartGarbageCollection.GarbageCollection.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectorLoginDTO {

    @NotBlank(message = "Collector ID is required")
    private String collectorId;

    @NotBlank(message = "Password is required")
    private String password;
}


//package com.SmartGarbageCollection.GarbageCollection.DTO;
//
//import lombok.Data;
//
//@Data
//public class CollectorLoginDTO {
//    private String collectorId;
//    private String password;
//}