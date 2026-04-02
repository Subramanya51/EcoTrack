package com.SmartGarbageCollection.GarbageCollection.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}


//package com.SmartGarbageCollection.GarbageCollection.DTO;
//import lombok.Data;
//
//@Data
//public class AdminLoginDTO {
//    private String email;
//    private String password;
//}