//package com.library.management.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class ImConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//
//                .authorizeRequests()
//                .requestMatchers("/login", "/static/**").permitAll() // 允许登录页和静态资源
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().disable()
//                .loginPage("/login") // 指定自定义登录页
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//
//        return http.build();
//    }
//}