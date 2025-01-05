package com.airbnb2.service;

import com.airbnb2.entity.PropertyUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.SQLOutput;
import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    private Algorithm algorithm;

    private final static String USER_NAME="username";

    @PostConstruct
    public void postConstruct(){
        // Print values to verify they are loaded correctly
        System.out.println("Algorithm Key: " + algorithmKey);
        System.out.println("Issuer: " + issuer);
        System.out.println("Expiry Time: " + expiryTime);

        // Initialize the algorithm with the key
        algorithm = Algorithm.HMAC256(algorithmKey); // Using HMAC256 algorithm with your secret key
    }

    // Method to generate JWT token
    public String generateToken(String username) {
        return JWT.create()
                .withClaim(USER_NAME, username)  // Use the passed username here
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryTime * 1000)) // Convert expiryTime to milliseconds
                .withIssuer(issuer)
                .sign(algorithm);
    }}