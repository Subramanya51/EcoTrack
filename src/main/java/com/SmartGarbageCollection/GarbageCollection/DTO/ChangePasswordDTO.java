package com.SmartGarbageCollection.GarbageCollection.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {

    @NotBlank(message = "Old password is required")
    private String oldPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 6, max = 50, message = "Password must be between 6 and 50 characters")
    private String newPassword;
}



//package com.SmartGarbageCollection.GarbageCollection.DTO;
//
//import lombok.Data;
//
//@Data
//public class ChangePasswordDTO {
//
//    private String oldPassword;
//    private String newPassword;
//}