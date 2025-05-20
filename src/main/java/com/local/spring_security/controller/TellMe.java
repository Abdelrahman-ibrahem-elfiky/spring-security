package com.local.spring_security.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tell")
public class TellMe {

    @PostMapping
    public String TellMeAbout()
    {
        return "welcome to my page, please tell me about myself";
    }
}
