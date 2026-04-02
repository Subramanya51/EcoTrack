package com.SmartGarbageCollection.GarbageCollection.Repository;

import com.SmartGarbageCollection.GarbageCollection.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserNameIgnoreCase(String userName);

    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByUserName(String userName);

    boolean existsByPhoneNumber(String phoneNumber);
}


////package com.SmartGarbageCollection.GarbageCollection.Repository;
////
////import com.SmartGarbageCollection.GarbageCollection.Entity.User;
////import org.springframework.data.jpa.repository.JpaRepository;
////import org.springframework.stereotype.Repository;
////
////import java.util.Optional;
////
////@Repository
////public interface UserRepository extends JpaRepository<User, Long> {
////    Optional<User> findByUserName(String userName);
////}
//package com.SmartGarbageCollection.GarbageCollection.Repository;
//
//import com.SmartGarbageCollection.GarbageCollection.Entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.Optional;
//
//public interface UserRepository extends JpaRepository<User, String> {
//
//    Optional<User> findByUserName(String userName);
//
//    Optional<User> findByPhoneNumber(String phoneNumber);
//}