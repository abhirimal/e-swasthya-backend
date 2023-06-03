package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;
import com.star.eswasthyabackend.service.user.appointment.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/save")
    public ResponseEntity<?> saveAndUpdate(@RequestBody AppointmentRequest appointmentRequest){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointment have been saved successfully.",
                appointmentService.saveAndUpdate(appointmentRequest)
        ));
    }

    @GetMapping("/view/{appointmentId}")
    public ResponseEntity<?> viewById(@PathVariable Integer appointmentId){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointment fetched successfully",
                appointmentService.viewById(appointmentId)
        ));
    }

    @GetMapping("/view-by-doctor/{doctorId}")
    public ResponseEntity<?> viewByDoctorId(@PathVariable Integer doctorId){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointments fetched successfully.",
                appointmentService.viewByDoctorid(doctorId)
        ));
    }

    @GetMapping("/view-by-patient/{patientId}")
    public ResponseEntity<?> viewByPatientId(@PathVariable Integer patientId){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointments feteched successfully",
                appointmentService.viewByPatiendId(patientId)
        ));
    }

    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<?> deleteById(@PathVariable Integer appointmentId){

        return ResponseEntity.ok(new ApiResponse(
           true,
           "Appointments fetched successfully.",
                appointmentService.deleteByAppointmentById(appointmentId)
        ));
    }


}
