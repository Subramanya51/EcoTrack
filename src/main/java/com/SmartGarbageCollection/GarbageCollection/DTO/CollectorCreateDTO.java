package com.SmartGarbageCollection.GarbageCollection.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectorCreateDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must be 10 digits"
    )
    private String phone;
}



//package com.SmartGarbageCollection.GarbageCollection.DTO;
//
//import lombok.Data;
//
//@Data
//public class CollectorCreateDTO {
//    private String name;
//    private String phone;
//}