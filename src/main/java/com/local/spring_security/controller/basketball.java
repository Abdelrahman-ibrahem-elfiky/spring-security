package com.local.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basketball")
public class basketball {


    public String start()
    {
        return "welcome basketball page";
    }
}
