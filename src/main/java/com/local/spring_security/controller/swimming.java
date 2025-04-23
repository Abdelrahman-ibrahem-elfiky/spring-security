package com.local.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swimming")
public class swimming {

    @GetMapping("/start")
    public String start()
    {
        return "welcome swimming page";
    }
}
