package com.thesquare.app.component;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // attempt a secured REST resource without supplying any credentials
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage() != null ? authException.getMessage() : "Unauthorized");
    }

}
