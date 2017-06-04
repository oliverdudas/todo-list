package com.thesquare.app.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

    String getUsername(String token);

    String generateToken(String subject);

    Boolean validateToken(String token, UserDetails userDetails);

}
