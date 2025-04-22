package com.local.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swimming")
public class swimming {


    public String start()
    {
        return "welcome swimming page";
    }
}
