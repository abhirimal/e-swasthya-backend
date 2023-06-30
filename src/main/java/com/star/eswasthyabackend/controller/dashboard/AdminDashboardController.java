package com.star.eswasthyabackend.controller.dashboard;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.service.dashboard.AdminDashboardService;
import com.star.eswasthyabackend.service.dashboard.impl.AdminDashboardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard/admin")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/get-patient-doctor-count")
    public ResponseEntity<?> getAdminDashboard(){
        return ResponseEntity.ok(new ApiResponse(true,
                "Dashboard data retrieved successfully",
                adminDashboardService.getUsersCount()
        ));
    }
}
