package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/admin")
    public ResponseEntity<?> getAdminDashboard(){
        return ResponseEntity.ok(new ApiResponse(true,
                "Dashboard data retrieved successfully",
                dashboardService.getAdminDashboard()));
    }

    @GetMapping("/doctor/{doctorDetailId}")
    public ResponseEntity<?> getDoctorDashboard(@PathVariable Integer doctorDetailId){
        return ResponseEntity.ok(new ApiResponse(true,
                "Dashboard data retrieved successfully",
                dashboardService.getDoctorDashboard(doctorDetailId)));
    }

    @GetMapping("/patient/{patientDetailId}")
    public ResponseEntity<?> getPatientDashboard(@PathVariable Integer patientDetailId){
        return ResponseEntity.ok(new ApiResponse(true,
                "Dashboard data retrieved successfully",
                dashboardService.getPatientDashboard(patientDetailId)));
    }


}
