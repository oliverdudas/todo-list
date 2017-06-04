package com.thesquare.app.service;

import com.thesquare.app.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenServiceImpl implements TokenService {

    public static final String USERNAME = "username";
    public static final String CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.prefix}")
    private String prefix;

    @Value("${jwt.expiration}")
    private long expiration;

    @Override
    public String getUsername(String token) {
        return (String) Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace(prefix, ""))
                .getBody()
                .get(USERNAME);
    }

    @Override
    public String generateToken(String subject) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME, subject);
        claims.put(CREATED, new Date());

        return prefix + " " + Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        final String username = getUsername(token);
        return username.equals(user.getUsername());
    }

}
