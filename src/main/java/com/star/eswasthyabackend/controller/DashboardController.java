package com.star.eswasthyabackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/dashboard")
public class DashboardController {

    @GetMapping("/admin")
    public String getAdminDashboard(){
        return "Hello Admin";
    }

    @GetMapping("/user")
    public String getUserDashboard(){
        return "Hello User";
    }


}
