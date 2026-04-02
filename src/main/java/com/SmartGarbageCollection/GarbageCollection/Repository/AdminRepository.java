package com.SmartGarbageCollection.GarbageCollection.Repository;

import com.SmartGarbageCollection.GarbageCollection.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);
}

//package com.SmartGarbageCollection.GarbageCollection.Repository;
//
//import com.SmartGarbageCollection.GarbageCollection.Entity.Admin;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface AdminRepository extends JpaRepository<Admin, Long> {
//
//    Optional<Admin> findByEmail(String email);
//
//    boolean existsByEmail(String email);
//}