package com.local.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basketball")
public class basketball {

    @GetMapping("/start")
    public String start()
    {
        return "welcome basketball page";
    }
}
