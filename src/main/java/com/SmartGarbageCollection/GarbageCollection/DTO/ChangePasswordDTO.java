package com.SmartGarbageCollection.GarbageCollection.DTO;

import lombok.Data;

@Data
public class ChangePasswordDTO {

    private String oldPassword;
    private String newPassword;
}