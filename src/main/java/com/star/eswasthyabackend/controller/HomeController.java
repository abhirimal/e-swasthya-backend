package com.star.eswasthyabackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/failureUrl")
    public String failure(){
        return "Authentication Failed";
    }




}
