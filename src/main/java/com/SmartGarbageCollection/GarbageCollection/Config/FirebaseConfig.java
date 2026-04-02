package com.SmartGarbageCollection.GarbageCollection.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class FirebaseConfig {

    @Value("${firebase.database.url}")
    private String databaseUrl;

    @Value("${firebase.credentials}")
    private String firebaseCredentials; // Base64 or raw JSON string

    @PostConstruct
    public void init() {
        try {

            if (!FirebaseApp.getApps().isEmpty()) {
                log.info("Firebase already initialized");
                return;
            }

            if (firebaseCredentials == null || firebaseCredentials.isEmpty()) {
                throw new RuntimeException("Firebase credentials not provided");
            }

            // Convert ENV string → InputStream
            InputStream serviceAccount = new ByteArrayInputStream(
                    firebaseCredentials.getBytes(StandardCharsets.UTF_8)
            );

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(databaseUrl)
                    .build();

            FirebaseApp.initializeApp(options);

            log.info("🔥 Firebase Connected Successfully");

        } catch (Exception e) {
            log.error("❌ Firebase initialization failed", e);
            throw new RuntimeException(e); // fail fast
        }
    }
}








































































































































































































































































































































//package com.SmartGarbageCollection.GarbageCollection.Config;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import jakarta.annotation.PostConstruct;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.InputStream;
//
//@Configuration
//public class FirebaseConfig {
//
//    @PostConstruct
//    public void init() {
//        try {
//            InputStream serviceAccount = getClass()
//                    .getClassLoader()
//                    .getResourceAsStream("ecosync-service-account.json");
//
//            FirebaseOptions options = FirebaseOptions.builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .setDatabaseUrl("https://ecosync-3a5c1-default-rtdb.firebaseio.com")
//                    .build();
//
//            if (FirebaseApp.getApps().isEmpty()) {
//                FirebaseApp.initializeApp(options);
//            }
//
//            System.out.println("🔥 Firebase Connected Successfully");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}