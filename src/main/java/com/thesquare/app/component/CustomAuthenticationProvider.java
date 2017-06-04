package com.thesquare.app.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String authUsername = authentication.getName();
        String authPassword = authentication.getCredentials().toString();

        try {
            final UserDetails userDetails = userDetailService.loadUserByUsername(authUsername);
            checkCredentials(authPassword, userDetails);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            throw new RuntimeException("Invalid username, or password.");
        }

        return authentication;
    }

    private void checkCredentials(String password, UserDetails userDetails) {
        if (!isValidPassword(password, userDetails)) {
            throw new BadCredentialsException("Bad credentials!");
        }
    }

    private boolean isValidPassword(String password, UserDetails userDetails) {
        return userDetails != null && userDetails.getPassword().equals(password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

}
