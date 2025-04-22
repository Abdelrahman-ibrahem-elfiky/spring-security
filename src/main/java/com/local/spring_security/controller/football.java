package com.local.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/football")
public class football {


    public String start()
    {
        return "welcome football page";
    }
}
