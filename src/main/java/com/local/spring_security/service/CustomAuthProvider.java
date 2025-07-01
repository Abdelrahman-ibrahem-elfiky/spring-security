package com.local.spring_security.service;

import com.local.spring_security.dao.SubscribeRepo;
import com.local.spring_security.model.Authority;
import com.local.spring_security.model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private SubscribeRepo subscribeRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthProvider(SubscribeRepo subscribeRepo ,PasswordEncoder passwordEncoder) {
        this.subscribeRepo = subscribeRepo;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        List<Subscriber> subscribers=subscribeRepo.findByEmail(username);
        if (subscribers.isEmpty())
        {
            throw new UsernameNotFoundException("this user in not found");
        }else {
            if (passwordEncoder.matches(password,subscribers.get(0).getPassword()))
            {
                //Before set authority
                //List<GrantedAuthority>authorityList=new ArrayList<>();
               // authorityList.add(new SimpleGrantedAuthority(subscribers.get(0).getRole()));
                return  new UsernamePasswordAuthenticationToken(username,password,getAuthorites(subscribers.get(0).getAuthorities()));
            }else {
                throw new BadCredentialsException("invalid password");
            }
        }
    }


    // get authority and convert to GrantedAuthority
    public List<SimpleGrantedAuthority>getAuthorites(List<Authority>authorities)
    {
        List<SimpleGrantedAuthority> simpleGrantedAuthority=new ArrayList<>();
        for (Authority authority:authorities)
        {
            simpleGrantedAuthority.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return simpleGrantedAuthority;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
