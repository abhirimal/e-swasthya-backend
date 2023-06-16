package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/admin")
    public ResponseEntity<?> getAdminDashboard(){
        return null;
    }

    @GetMapping("/doctor")
    public ResponseEntity<?> getDoctorDashboard(){
        return null;
    }

    @GetMapping("/patient")
    public ResponseEntity<?> getPatientDashboard(){
        return null;
    }


}
