package com.local.spring_security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubscriberSec implements UserDetails {


    private Subscriber subscriber;

    public SubscriberSec(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority>authorities=new ArrayList<>();
        // authorities.add(new SimpleGrantedAuthority(subscriber.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return subscriber.getPassword();
    }

    @Override
    public String getUsername() {
        return subscriber.getEmail();
    }

}
