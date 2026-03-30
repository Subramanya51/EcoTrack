package com.SmartGarbageCollection.GarbageCollection.Controller;

import com.SmartGarbageCollection.GarbageCollection.DTO.CollectorLoginDTO;
import com.SmartGarbageCollection.GarbageCollection.Service.CollectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collector/auth")
@RequiredArgsConstructor
public class CollectorController {

    private final CollectorService collectorService;

    @PostMapping("/login")
    public String login(@RequestBody CollectorLoginDTO dto) {

        return collectorService.login(
                dto.getCollectorId(),
                dto.getPassword()
        );
    }
}