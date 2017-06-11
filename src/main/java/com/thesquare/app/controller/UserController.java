package com.thesquare.app.controller;

import com.thesquare.app.model.User;
import com.thesquare.app.service.TokenService;
import com.thesquare.app.util.RequestMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenService tokenService;

    @Value("${jwt.header}")
    private String header;

    @RequestMapping(
            value = RequestMappings.API_USERS,
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getUser(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(header);
        User user = (User) userDetailsService.loadUserByUsername(tokenService.getUsername(token));
        return ResponseEntity.ok(user);
    }

}
