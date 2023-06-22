package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.enums.AppointmentStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/list-appointment-status")
    public ResponseEntity<?> getAppointmentStatusList() {

        return ResponseEntity.ok(new ApiResponse(true,
                "List of appointment status fetched successfully.",
                Arrays.asList(AppointmentStatus.values())
        ));
    }
}
