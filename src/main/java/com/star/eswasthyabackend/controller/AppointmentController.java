package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.temp.IdSmsDto;
import com.star.eswasthyabackend.dto.temp.SmsApiResponse;
import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;
import com.star.eswasthyabackend.dto.appointment.UpdateAppointmentApprovalDto;
import com.star.eswasthyabackend.service.appointment.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        IdSmsDto idSmsDto = appointmentService.save(appointmentRequest);
        String otp = idSmsDto.getOtp();
        Integer id = idSmsDto.getId();
        return ResponseEntity.ok(new SmsApiResponse(
                true,
                "Appointment have been saved successfully.",
                id,
                otp
        ));
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOTP(@Valid @RequestBody UpdateAppointmentApprovalDto approvalDto) {
        IdSmsDto idSmsDto = appointmentService.resendOTP(approvalDto);
        String otp = idSmsDto.getOtp();
        Integer id = idSmsDto.getId();

        return ResponseEntity.ok(new SmsApiResponse(
                true,
                "Sms otp sent successfully",
                id,
                otp
        ));
    }

    @GetMapping("/verify-appointment-otp")
    public ResponseEntity<?> verifyAppointmentByOtp(@RequestParam Integer appointmentId, @RequestParam String otp) {

        return ResponseEntity.ok(new ApiResponse(true,
                "Otp verified successfully.",
                appointmentService.verifyAppointmentByOtp(appointmentId, otp)));
    }

    @GetMapping("/view/{appointmentId}")
    public ResponseEntity<?> viewById(@PathVariable Integer appointmentId) {

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointment fetched successfully",
                appointmentService.viewById(appointmentId)
        ));
    }

    @GetMapping("/view-by-doctor")
    public ResponseEntity<?> viewByDoctorId(@RequestParam Integer doctorId, @RequestParam String status) {

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointment fetched successfully.",
                appointmentService.viewByDoctorId(doctorId, status)
        ));
    }

    @GetMapping("/view-by-patient")
    public ResponseEntity<?> viewByPatientId(@RequestParam Integer patientId, @RequestParam String status) {

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointment fetched successfully",
                appointmentService.viewByPatientId(patientId, status)
        ));
    }

    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<?> deleteById(@PathVariable Integer appointmentId) {

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointment deleted successfully.",
                appointmentService.deleteByAppointmentById(appointmentId)
        ));
    }

    @PatchMapping("/update-appointment-approval")
    public ResponseEntity<?> updateAppointmentApproval(@Valid @RequestBody UpdateAppointmentApprovalDto approvalDto) {

        return ResponseEntity.ok(new ApiResponse(
                true,
                "Appointment updated successfully.",
                appointmentService.updateAppointmentApproval(approvalDto)
        ));
    }
}
