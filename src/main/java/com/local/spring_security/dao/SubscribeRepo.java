package com.local.spring_security.dao;

import com.local.spring_security.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeRepo extends JpaRepository<Subscriber,Long> {

    List<Subscriber>findByEmail(String email);
}
