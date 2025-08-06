package com.local.spring_security.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.internal.HEMLogging;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String KEY="abdelrahman ibrahim abdo elfiky ";
    private static final String HADER="Jwt-Token";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            System.out.println("inside jwt filter ");
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            if (authentication!=null)
            {
                SecretKey secretKey= Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
                String jwt= Jwts.builder().setSubject("jwt token")
                        .claim("email",authentication.getName())
                        .claim("authorities",authorities(authentication.getAuthorities()))
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(new Date().getTime()+30000))
                        .signWith(secretKey)
                        .compact();
                response.setHeader(HADER,jwt);
            }
        }catch (Exception e)
        {
            System.out.println(e.toString());
        }

        filterChain.doFilter(request,response);
    }
    private String authorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auth = new HashSet<>();
        for (GrantedAuthority authority:authorities)
            auth.add(authority.getAuthority());
        return String.join(",",auth);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // this filter is disable when any request not that
        return !request.getServletPath().equals("/api/sign");
    }
}
