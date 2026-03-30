package com.SmartGarbageCollection.GarbageCollection.Service;

import com.SmartGarbageCollection.GarbageCollection.DTO.DailyStatsDTO;
import com.SmartGarbageCollection.GarbageCollection.Entity.PickupStatus;
import com.SmartGarbageCollection.GarbageCollection.Repository.PickupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class AdminStatsServiceImpl implements AdminStatsService {

    private final PickupRepository pickupRepository;

    @Override
    public DailyStatsDTO getTodayStats() {

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));

        long total = pickupRepository.countByRequestDate(today);

        long collected = pickupRepository.countByRequestDateAndStatus(
                today, PickupStatus.COLLECTED);

        long notCollected = pickupRepository.countByRequestDateAndStatus(
                today, PickupStatus.NOT_COLLECTED);

        long active = pickupRepository.countByRequestDateAndStatusIsNull(today);

        return new DailyStatsDTO(total, collected, notCollected, active);
    }
}