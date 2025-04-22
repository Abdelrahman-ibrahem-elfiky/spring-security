package com.local.spring_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suscribers")
public class suscribers {


    public String start()
    {
        return "welcome suscribers page";
    }
}
