package com.SmartGarbageCollection.GarbageCollection.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}