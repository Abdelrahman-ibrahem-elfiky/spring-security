package com.local.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class About {


    public String start()
    {
        return "welcome about page";
    }
}
