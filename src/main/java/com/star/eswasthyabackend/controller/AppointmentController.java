package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;
import com.star.eswasthyabackend.dto.appointment.UpdateAppointmentApprovalDto;
import com.star.eswasthyabackend.service.appointment.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AppointmentRequest appointmentRequest){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointment have been saved successfully.",
                appointmentService.save(appointmentRequest)
        ));
    }

    @GetMapping("/verify-appointment-otp")
    public ResponseEntity<?> verifyAppointmentByOtp(@RequestParam Integer appointmentId, @RequestParam String otp){

        return ResponseEntity.ok(new ApiResponse(true,
                "Otp verified successfully.",
                appointmentService.verifyAppointmentByOtp(appointmentId, otp)));
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
                "Appointment fetched successfully.",
                appointmentService.viewByDoctorId(doctorId)
        ));
    }

    @GetMapping("/view-by-patient/{patientId}")
    public ResponseEntity<?> viewByPatientId(@PathVariable Integer patientId){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointment fetched successfully",
                appointmentService.viewByPatientId(patientId)
        ));
    }

    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<?> deleteById(@PathVariable Integer appointmentId){

        return ResponseEntity.ok(new ApiResponse(
           true,
           "Appointment deleted successfully.",
                appointmentService.deleteByAppointmentById(appointmentId)
        ));
    }

    @PatchMapping("/update-appointment-approval")
    public ResponseEntity<?> updateAppointmentApproval(@RequestBody UpdateAppointmentApprovalDto approvalDto){

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointment updated successfully.",
                appointmentService.updateAppointmentApproval(approvalDto)
        ));
    }


}
