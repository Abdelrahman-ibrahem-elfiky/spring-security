package com.local.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
public class SpringSecurity{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(auth->auth
                .requestMatchers("/pub/**").permitAll()
                .anyRequest().authenticated()
        ).formLogin().and().httpBasic();
        return http.build() ;
    }

//    store user by memory

//    @Bean
//    public UserDetailsService userDetails()
//    {
//        UserDetails user1= User.withUsername("abdelrahman")
//                .password("test")
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2= User.withUsername("ahmed")
//                .password("test")
//                .roles("USER")
//                .build();
//        UserDetails user3= User.withUsername("mohamed")
//                .password("test")
//                .roles("TEACHER")
//                .build();
//        return new InMemoryUserDetailsManager(user1,user2,user3);
//
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource)
//    {
//        return new JdbcUserDetailsManager(dataSource);
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
