package com.SmartGarbageCollection.GarbageCollection.Service;
import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorCreateDTO;
import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorResponseDTO;

public interface CollectorService {

    CollectorResponseDTO createCollector(CollectorCreateDTO dto, String adminEmail);
    // 🔥 ADD THIS
    String login(String collectorId, String password);
}