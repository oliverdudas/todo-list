package com.thesquare.app.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenServiceImpl implements TokenService {

    public static final String USERNAME = "username";
    public static final String CREATED = "created";

    @Override
    public String getTokenSubject(String token, String secret, String prefix) {
        return (String) Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace(prefix, ""))
                .getBody()
                .get(USERNAME);
    }

    @Override
    public String generateToken(String subject, String secret, String prefix, long expiration) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME, subject);
        claims.put(CREATED, new Date());

        return prefix + " " + Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
