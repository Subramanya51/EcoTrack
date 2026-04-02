package com.SmartGarbageCollection.GarbageCollection.Service;

import com.SmartGarbageCollection.GarbageCollection.DTO.AdminRegisterDTO;

public interface AdminService {

    // 🔹 Register new admin (returns success message or token)
    String registerAdmin(AdminRegisterDTO dto);
}


//package com.SmartGarbageCollection.GarbageCollection.Service;
//
//import com.SmartGarbageCollection.GarbageCollection.DTO.AdminRegisterDTO;
//
//public interface AdminService {
//
//    String registerAdmin(AdminRegisterDTO dto);
//}