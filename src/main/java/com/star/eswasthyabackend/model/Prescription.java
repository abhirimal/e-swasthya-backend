package com.star.eswasthyabackend.model;

import com.star.eswasthyabackend.dto.prescription.PrescriptionRequestDto;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Medication_SEQ_GEN")
    @SequenceGenerator(name = "Medication_SEQ_GEN", sequenceName = "Medication_SEQ", allocationSize = 1)
    private Integer id;

    private String medicineName;

    private Double dosageInUnit;

    private String frequencyPerDay;

    private String additionalNote;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer durationInDays;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "diagnosis_id")
    private Diagnosis diagnosis;

    @OneToOne
    @JoinColumn(name = "patient_detail_id")
    private PatientDetails patientDetail;

    @OneToOne
    @JoinColumn(name = "doctor_detail_id")
    private DoctorDetails doctorDetail;

    public Prescription(PrescriptionRequestDto newPrescription, Diagnosis diagnosis, DoctorDetails doctorDetail, PatientDetails patientDetail) {
        medicineName = newPrescription.getMedicineName();
        dosageInUnit = newPrescription.getDosageInUnit();
        frequencyPerDay = newPrescription.getFrequencyPerDay();
        additionalNote = newPrescription.getAdditionalNote();
        startDate = newPrescription.getStartDate();
        endDate = newPrescription.getStartDate().plusDays(newPrescription.getDurationInDays());
        durationInDays = newPrescription.getDurationInDays();
        isActive = LocalDate.now().isBefore(endDate);
        this.patientDetail = patientDetail;
        this.doctorDetail = doctorDetail;
        this.diagnosis = diagnosis;
    }
}
