package com.example.Authentication.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.io.Decoders;


import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    public  String generateToken(String username)
    {
        Map<String,Object> Claims=new HashMap<>();

        return Jwts
                .builder()
                .setClaims(Claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 *60*60 ))
                .signWith(secretKey).compact();
    }

//    public Key getSignKey()
//    {
//        byte[] KeyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(KeyBytes);
//    }
}
