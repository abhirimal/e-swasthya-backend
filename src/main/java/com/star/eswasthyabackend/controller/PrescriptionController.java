package com.star.eswasthyabackend.controller;

import com.star.eswasthyabackend.service.prescription.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

}
