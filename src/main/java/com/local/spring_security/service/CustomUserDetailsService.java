package com.local.spring_security.service;

import com.local.spring_security.dao.SubscribeRepo;
import com.local.spring_security.model.Subscriber;
import com.local.spring_security.model.SubscriberSec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private SubscribeRepo subscribeRepo;

    @Autowired
    public CustomUserDetailsService(SubscribeRepo subscribeRepo) {
        this.subscribeRepo = subscribeRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<Subscriber> subscriber= subscribeRepo.findByEmail(username);
        if (subscriber.isEmpty())
        {
            throw  new UsernameNotFoundException("this email not found");
        }
        return new SubscriberSec(subscriber.get(0));
    }
}
