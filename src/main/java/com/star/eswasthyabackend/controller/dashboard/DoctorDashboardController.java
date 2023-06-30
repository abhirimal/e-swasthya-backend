package com.star.eswasthyabackend.controller.dashboard;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.service.appointment.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dashboard/doctor")
public class DoctorDashboardController {

    private final AppointmentService appointmentService;

    @GetMapping("/get-appointment-count")
    public ResponseEntity<?> getAppointmentCountPerMonth(){

        return ResponseEntity.ok(new ApiResponse(true,
                "Appointments count list fetched successfully.",
                appointmentService.getAppointmentCount()));
    }
}
