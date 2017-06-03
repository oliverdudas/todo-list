package com.thesquare.app.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesquare.app.model.AccountCredentials;
import com.thesquare.app.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private Environment env;

    public JWTLoginFilter() {
        super(new AntPathRequestMatcher("/login"));
    }

    @PostConstruct
    private void setAuthenticationManager() {
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        AccountCredentials credentials = readCredentials(req);
        return getAuthenticationManager().authenticate(
                createToken(credentials)
        );
    }

    private AccountCredentials readCredentials(HttpServletRequest req) throws IOException {
        return new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);
    }

    private UsernamePasswordAuthenticationToken createToken(AccountCredentials credentials) {
        return new UsernamePasswordAuthenticationToken(
                credentials.getUsername(),
                credentials.getPassword(),
                Collections.emptyList()
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String token = tokenService.generateToken(
                auth.getName(),
                env.getProperty("jwt.secret"),
                env.getProperty("jwt.prefix"),
                env.getProperty("jwt.expiration", Long.class)
        );
        res.addHeader(env.getProperty("jwt.header"), token);
    }
}
