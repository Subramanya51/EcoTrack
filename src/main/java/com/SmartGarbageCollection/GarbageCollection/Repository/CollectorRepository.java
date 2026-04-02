package com.SmartGarbageCollection.GarbageCollection.Repository;

import com.SmartGarbageCollection.GarbageCollection.Entity.Collector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollectorRepository extends JpaRepository<Collector, Long> {

    Optional<Collector> findByCollectorIdIgnoreCase(String collectorId);

    boolean existsByCollectorId(String collectorId);

    boolean existsByPhone(String phone);
}

//package com.SmartGarbageCollection.GarbageCollection.Repository;
//import com.SmartGarbageCollection.GarbageCollection.Entity.Collector;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.Optional;
//
//public interface CollectorRepository extends JpaRepository<Collector, Long> {
//
//    Optional<Collector> findByCollectorId(String collectorId);
//
//    boolean existsByPhone(String phone);
//}