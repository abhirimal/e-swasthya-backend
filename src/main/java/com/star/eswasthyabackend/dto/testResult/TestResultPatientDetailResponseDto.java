package com.star.eswasthyabackend.dto.testResult;

import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResultPatientDetailResponseDto {

    private Integer patientDetailId;

    private String medicalRecordNumber;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
    public TestResultPatientDetailResponseDto(PatientDetails patientDetail) {
        patientDetailId = patientDetail.getPatientDetailId();
        medicalRecordNumber = patientDetail.getMedicalRecordNumber();
        firstName = patientDetail.getFirstName();
        lastName = patientDetail.getLastName();;
        email = patientDetail.getEmail();
        phoneNumber = patientDetail.getPhoneNumber();
    }
}
