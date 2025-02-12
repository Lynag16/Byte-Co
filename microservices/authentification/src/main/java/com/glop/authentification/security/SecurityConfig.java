//package com.glop.authentification.security;




//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable() 
//            .authorizeHttpRequests(auth -> auth
//            		.requestMatchers("/h2-console/**").permitAll()
//            		.requestMatchers("/api/clients/register", "/api/clients/login").permitAll()
//                .anyRequest().authenticated()
//            )
//            .formLogin()
//                .permitAll()
//            .and()
//            .logout()
//                .permitAll();
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}







