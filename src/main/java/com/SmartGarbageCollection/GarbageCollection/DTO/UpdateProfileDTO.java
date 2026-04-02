package com.SmartGarbageCollection.GarbageCollection.DTO;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileDTO {

    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must be 10 digits"
    )
    private String phoneNumber;

    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;
}


//package com.SmartGarbageCollection.GarbageCollection.DTO;
//
//import lombok.Data;
//
//@Data
//public class UpdateProfileDTO {
//    private String userName;
//    private String phoneNumber;
//    private String address;
//}
