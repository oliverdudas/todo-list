package com.thesquare.app.config;

import com.thesquare.app.component.AuthenticationEntryPointImpl;
import com.thesquare.app.component.CustomAuthenticationProvider;
import com.thesquare.app.filter.AuthenticationFilter;
import com.thesquare.app.util.RequestMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@SuppressWarnings("SpringJavaAutowiringInspection")
@ComponentScan("com.thesquare.app.service")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Bean
    public AuthenticationFilter authenticationFilterBean() throws Exception {
        return new AuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, RequestMappings.API_ALL).authenticated()
                .antMatchers(HttpMethod.POST, RequestMappings.API_ALL).authenticated()
                .antMatchers(HttpMethod.PUT, RequestMappings.API_ALL).authenticated()
                .antMatchers(HttpMethod.DELETE, RequestMappings.API_ALL).authenticated()
                .antMatchers(HttpMethod.OPTIONS, RequestMappings.API_ALL).permitAll()
                .and()
                .addFilterBefore(authenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

}
