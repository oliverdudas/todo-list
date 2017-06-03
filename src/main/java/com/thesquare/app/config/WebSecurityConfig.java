package com.thesquare.app.config;

import com.thesquare.app.component.AuthenticationEntryPointImpl;
import com.thesquare.app.filter.AuthenticationFilter;
import com.thesquare.app.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public LoginFilter loginFilterBean() throws Exception {
        return new LoginFilter();
    }

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
                .antMatchers("/api/**").authenticated()
//                .authorizeRequests()
//                .antMatchers("/", "/*.js", "/.css").permitAll()
//                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
//                .anyRequest().authenticated()
                .and()
                // api/login requests
                .addFilterBefore(loginFilterBean(), UsernamePasswordAuthenticationFilter.class)
                // other requests to check the presence of JWT in header
                .addFilterBefore(authenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }

    /**
     * InMemoryAuthentication for demonstration purposes only
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Create a default account
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("a")
                .roles("ADMIN");

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("u")
                .roles("USER");
    }

}
