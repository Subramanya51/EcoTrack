package com.SmartGarbageCollection.GarbageCollection.Service;

import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorCreateDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorListDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorResponseDTO;

import java.util.List;

public interface CollectorService {

    // 🔹 Create collector (by admin)
    CollectorResponseDTO createCollector(CollectorCreateDTO dto, String adminEmail);

    // 🔹 Collector login (returns JWT token)
    String login(String collectorId, String password);
    List<CollectorListDTO> getAllCollectors();
}





//package com.SmartGarbageCollection.GarbageCollection.Service;
//import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorCreateDTO;
//import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorResponseDTO;
//
//public interface CollectorService {
//
//    CollectorResponseDTO createCollector(CollectorCreateDTO dto, String adminEmail);
//    // 🔥 ADD THIS
//    String login(String collectorId, String password);
//}