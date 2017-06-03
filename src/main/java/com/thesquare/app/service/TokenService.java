package com.thesquare.app.service;

public interface TokenService {

    String getTokenSubject(String token, String secret, String prefix);

    String generateToken(String subject, String secret, String prefix, long expiration);
}
