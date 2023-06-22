package com.star.eswasthyabackend.controller.user.doctor;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.JwtResponse;
import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailsRequestDto;
import com.star.eswasthyabackend.service.user.doctor.DoctorDetailsService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/doctor")
public class DoctoDetailsController {

    private final DoctorDetailsService doctorDetailsService;

    @PostMapping("/save")
    ResponseEntity<?> saveAndUpdate(@RequestBody DoctorDetailsRequestDto requestDto){
        String jwt = doctorDetailsService.saveDoctorDetails(requestDto);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/view/{id}")
    ResponseEntity<?> viewById(@PathVariable Integer id){
        return ResponseEntity.ok(new ApiResponse(true,
                "Doctor details fetched successfully",
                doctorDetailsService.findById(id)));
    }

    @GetMapping("/view-all")
    ResponseEntity<?> listAllDoctos(){
        return ResponseEntity.ok(new ApiResponse(true,
                "Doctors list fetched successfully",
                doctorDetailsService.listAllDoctor()));
    }
}
