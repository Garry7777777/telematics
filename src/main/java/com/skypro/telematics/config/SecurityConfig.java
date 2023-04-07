package com.skypro.telematics.config;

import com.skypro.telematics.filter.UUIDAuthenticationFilter;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.*;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UUIDAuthenticationFilter uuidAuthenticationFilter;

    public SecurityConfig(UUIDAuthenticationFilter uuidAuthenticationFilter) {
        this.uuidAuthenticationFilter = uuidAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain configureSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                    .anonymous()
                        .and()
//                .formLogin(form -> form.loginPage("/indications/login").permitAll())
//                .formLogin().permitAll(HttpMethod.POST)
                .httpBasic()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                .addFilterBefore(uuidAuthenticationFilter, ExceptionTranslationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/indications/{serial}")
                    .permitAll()
                .antMatchers(HttpMethod.POST,"/indications/login")
                    .permitAll()
                .anyRequest()
                .authenticated();

        return httpSecurity.build();


    }
}