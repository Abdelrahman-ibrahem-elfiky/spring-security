package com.local.spring_security.filter;

import com.local.spring_security.constant.SecConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtValidationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt=request.getHeader(SecConstant.HEADER);
        if (jwt!=null){
            try {
                SecretKey secretKey= Keys.hmacShaKeyFor(SecConstant.KEY.getBytes(StandardCharsets.UTF_8));
                Claims claims= Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                String email=String.valueOf(claims.get("email"));
                String authorities= (String) claims.get("authorities");

                Authentication authentication=new UsernamePasswordAuthenticationToken(email,null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                SecurityContextHolder.getContext().setAuthentication(authentication);


            }catch (Exception e)
            {
                throw new BadRequestException("invalid token");
            }

        }
        filterChain.doFilter(request,response);
    }
}
