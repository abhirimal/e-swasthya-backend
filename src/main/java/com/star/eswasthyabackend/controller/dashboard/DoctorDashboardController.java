package com.star.eswasthyabackend.controller.dashboard;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.service.appointment.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dashboard/doctor")
public class DoctorDashboardController {

    private final AppointmentService appointmentService;

    @GetMapping("/get-appointment-count")
    public ResponseEntity<?> getAppointmentCountPerMonth(@RequestParam("doctorDetailId") Integer doctorDetailId){

        return ResponseEntity.ok(new ApiResponse(true,
                "Appointments count list fetched successfully.",
                appointmentService.getAppointmentCount(doctorDetailId)));
    }
}
