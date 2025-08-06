package com.local.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SignIn {

    @GetMapping("/sign")
    public String signIn()
    {
        return "welcome to app";
    }
}
