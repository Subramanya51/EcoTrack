package com.SmartGarbageCollection.GarbageCollection.Service;

import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorCreateDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorListDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorResponseDTO;

import java.util.List;

public interface CollectorService {

    CollectorResponseDTO createCollector(CollectorCreateDTO dto, String adminEmail);

    String login(String collectorId, String password);

    List<CollectorListDTO> getAllCollectors();

    List<CollectorListDTO> getCollectorsByAdmin(String adminEmail);
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