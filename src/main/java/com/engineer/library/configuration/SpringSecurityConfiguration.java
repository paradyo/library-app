package com.engineer.library.configuration;

import com.engineer.library.service.CustomAuthenticationEntryPoint;
import com.engineer.library.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;


import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration extends AbstractSecurityWebApplicationInitializer {

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/books/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**") .permitAll()
                        .anyRequest().authenticated()
                );
        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.sessionManagement(
                session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )
        );

        httpSecurity.csrf().disable().cors();
        httpSecurity.headers().frameOptions().disable();

        return httpSecurity.build();
    }
}
