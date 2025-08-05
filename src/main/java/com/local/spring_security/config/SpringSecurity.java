package com.local.spring_security.config;

import com.local.spring_security.filter.FilterAfter;
import com.local.spring_security.filter.FilterBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.GenericFilterBean;

import javax.sql.DataSource;

@Configuration
public class SpringSecurity{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                // stateless jsessionIp
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //to create token for me by csrf to prevent any attack
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //to create csrf token and ignore a specific api called "tell"
                //.csrf().ignoringRequestMatchers("/tell/**").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.and()
                //XSRF enable prevent any attack from take my data
                .csrf().disable()
                //custom filters         me
                .addFilterBefore(new FilterBefore(), BasicAuthenticationFilter.class)
                .addFilterAfter(new FilterAfter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(auth->auth
                .requestMatchers("/pub/**").permitAll()
                .requestMatchers("/football/**").hasAuthority("READ")
                .requestMatchers("/swimming/**").hasAuthority("WRITE")
                .requestMatchers("/basketball/**").hasAnyAuthority("DELETE","READ")
                //.anyRequest().authenticated()
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



        @Bean
        public UserDetailsService userDetailsService(DataSource dataSource)
        {
            return new JdbcUserDetailsManager(dataSource);
        }
    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
}
