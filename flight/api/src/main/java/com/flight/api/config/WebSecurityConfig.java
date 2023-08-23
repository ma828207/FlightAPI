
package com.flight.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


/**
 * This class is used for endpoints authorization and authentication and secures all endpoints with HTTP Basic.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    /**
     * This method configures http security to the endpoint urls
     *
     * @param http
     * @return SecurityFilterChain to determine Spring Security Filter instances
     * to be invoked for the request.
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }
}


