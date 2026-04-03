package com.SmartGarbageCollection.GarbageCollection.Utilis;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtility {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpiration; // in milliseconds

    // 🔑 Generate signing key
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 🔐 Generate JWT Token
    public String generateToken(String userName) {

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 📥 Extract Username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // 📥 Extract Expiration
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 🔍 Extract Any Claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 🔍 Extract All Claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .setAllowedClockSkewSeconds(60) // handles server time differences
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Validate Token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // ⏰ Check Expiration
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}





























































































































































































































































































































//package com.SmartGarbageCollection.GarbageCollection.Utilis;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;

//
//import javax.crypto.SecretKey;
//import java.util.Date;
//
//@Component
//public class JwtUtility
//{
//    private final String SECRET = "9f8c3e7a2d4b1f6c8a9e0b3d5f7a1c2e4b6d8f0a9c3e5b7d1f2a4c6e8b0d9f1";
//    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
//    public String generateToken(String userName)
//    {
//
//        return Jwts.builder()
//                    . setSubject(userName)
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
//                    .signWith(key, SignatureAlgorithm.HS256)
//                    .compact();
//
//    }
//    public String extractUserName(String token)
//    {
//        return extractClaims(token).getSubject();
//    }
//
//    private Claims extractClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        String userName = extractUserName(token);
//        return userName.equals(userDetails.getUsername()) && !istokenExpired(token);
//    }
//    private boolean istokenExpired(String token)
//    {
//        return extractClaims(token).getExpiration().before(new Date());
//    }
//}
