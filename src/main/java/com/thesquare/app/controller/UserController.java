package com.thesquare.app.controller;

import com.thesquare.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(
            value = "/users/{username}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getUser(@PathVariable String username) throws AuthenticationException {
        User user = (User) userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(user);
    }

}
