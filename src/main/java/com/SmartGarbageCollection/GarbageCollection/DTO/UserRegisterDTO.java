package com.SmartGarbageCollection.GarbageCollection.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50)
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 50)
    private String password;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phoneNumber;

    private String address;
}