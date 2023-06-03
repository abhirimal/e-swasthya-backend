package com.star.eswasthyabackend.service.user.appointment;

import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.Appointment;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.user.AppointmentRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    private final PatientDetailsRepository patientDetailsRepository;

    private final DoctorDetailsRepository doctorDetailsRepository;

    @Override
    public Integer saveAndUpdate(AppointmentRequest appointmentRequest) {

        PatientDetails patient = patientDetailsRepository.findById(appointmentRequest.getPatientDetailId())
                .orElseThrow(()-> new AppException("Patient not found for given id", HttpStatus.BAD_REQUEST));

        DoctorDetails doctor = doctorDetailsRepository.findById(appointmentRequest.getDoctorDetailId())
                .orElseThrow(()-> new AppException("Doctor not found for given id", HttpStatus.BAD_REQUEST));


        Appointment appointment;

        if(appointmentRequest.getId()!=null) {
            appointment = appointmentRepository.findById(appointmentRequest.getId())
                    .orElseThrow(()-> new AppException("Appointment not found for given id", HttpStatus.BAD_REQUEST));
        }
        else {
            appointment = new Appointment();
        }

        appointment.setDoctorDetails(doctor);
        appointment.setPatientDetails(patient);
        appointment.setAppointmentTime(appointmentRequest.getAppointmentTime());
        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
        appointment.setHospitalName(appointmentRequest.getHospitalName());
        appointment.setReasonForVisit(appointmentRequest.getReasonForVisit());
        appointment.setIsActive(true);
        appointmentRepository.saveAndFlush(appointment);
        return appointment.getId();
    }

    @Override
    public Map<String, Object> viewById(Integer appointmentId) {
        return appointmentRepository.viewById(appointmentId);
    }

    @Override
    public List<Map<String, Object>> viewByDoctorid(Integer doctorId) {
        return appointmentRepository.viewByDoctorId(doctorId);
    }

    @Override
    public List<Map<String, Object>> viewByPatiendId(Integer patientId) {
        return appointmentRepository.viewByPatientId(patientId);
    }

    @Override
    public Boolean deleteByAppointmentById(Integer appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppException("Appointment not found for given id.", HttpStatus.BAD_REQUEST));
        appointment.setIsActive(false);
        appointmentRepository.save(appointment);
        return true;
    }
}
