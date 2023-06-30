package com.star.eswasthyabackend.service.appointment;

import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;
import com.star.eswasthyabackend.dto.appointment.UpdateAppointmentApprovalDto;
import com.star.eswasthyabackend.dto.temp.IdSmsDto;
import com.star.eswasthyabackend.enums.AppointmentStatus;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.Appointment;
import com.star.eswasthyabackend.model.Hospital;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.AppointmentRepository;
import com.star.eswasthyabackend.repository.HospitalRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import com.star.eswasthyabackend.service.sms.SmsService;
import com.star.eswasthyabackend.service.sms.SociairSmsService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    private final PatientDetailsRepository patientDetailsRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;
    private final SmsService smsService;

    @Override
    @Transactional
    public IdSmsDto save(AppointmentRequest appointmentRequest) {

        PatientDetails patient = patientDetailsRepository.findById(appointmentRequest.getPatientDetailId())
                .orElseThrow(()-> new AppException("Patient not found for given id", HttpStatus.BAD_REQUEST));

        DoctorDetails doctor = doctorDetailsRepository.findById(appointmentRequest.getDoctorDetailId())
                .orElseThrow(()-> new AppException("Doctor not found for given id", HttpStatus.BAD_REQUEST));

        Hospital hospital = hospitalRepository.findById(appointmentRequest.getHospitalId())
                .orElseThrow(()-> new AppException("Hospital not found for given id", HttpStatus.BAD_REQUEST));


        Appointment appointment;

        if(appointmentRequest.getId()!=null) {
            appointment = appointmentRepository.findById(appointmentRequest.getId())
                    .orElseThrow(()-> new AppException("Appointment not found for given id", HttpStatus.BAD_REQUEST));
        }
        else {
            appointment = new Appointment();
        }

        appointment.setReasonForVisit(appointmentRequest.getReasonForVisit());
        appointment.setDoctorDetails(doctor);
        appointment.setPatientDetails(patient);
        appointment.setAppointmentTime(appointmentRequest.getAppointmentTime());
        appointment.setAppointmentDate(appointmentRequest.getAppointmentDate());
        appointment.setHospital(hospital);
        appointment.setIsDiagnosisFilled(false);
        appointment.setStatus(AppointmentStatus.CREATED);

        //send sms
//        String otp = RandomString.make(6);
        Random random = new Random();
        int otp = random.nextInt(9000) + 1000;
        String message = "Dear "+patient.getFirstName() +", Please use OTP: " +otp +" to confirm your appointment ";
        smsService.sendSms(patient.getPhoneNumber(), message);

        appointment.setOtpCode(String.valueOf(otp));
        appointment.setOtpGenTime(LocalTime.now());
        appointmentRepository.saveAndFlush(appointment);

        IdSmsDto idSmsDto = new IdSmsDto();
        idSmsDto.setOtp(message);
        idSmsDto.setId(appointment.getId());
        return idSmsDto;
    }

    @Override
    public Map<String, Object> viewById(Integer appointmentId) {
        return appointmentRepository.viewById(appointmentId);
    }

    @Override
    public List<Map<String, Object>> viewByDoctorId(Integer doctorId, String status) {
        return appointmentRepository.viewByDoctorIdAndStatus(doctorId, status);
    }

    @Override
    public List<Map<String, Object>> viewByPatientId(Integer patientId, String status) {
        return appointmentRepository.viewByPatientIdAndStatus(patientId, status);
    }

    @Override
    public Boolean deleteByAppointmentById(Integer appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppException("Appointment not found for given id.", HttpStatus.BAD_REQUEST));
        appointment.setStatus(AppointmentStatus.DELETED);
        appointmentRepository.save(appointment);
        return true;
    }

    @Override
    public Boolean updateAppointmentApproval(UpdateAppointmentApprovalDto approvalDto) {
        Appointment appointment = appointmentRepository.findById(approvalDto.getAppointmentId())
                .orElseThrow(()-> new AppException("Appointment not found for given id.", HttpStatus.BAD_REQUEST));

        if(!Objects.equals(appointment.getPatientDetails().getPatientDetailId(), approvalDto.getPatientId())
        || !Objects.equals(appointment.getDoctorDetails().getDoctorDetailId(), approvalDto.getDoctorId())){
            throw new AppException("Either Patient or Doctor is not associated with this appointment", HttpStatus.BAD_REQUEST);
        }
        appointment.setStatus(approvalDto.getStatus());
        appointmentRepository.save(appointment);
        return true;
    }

    @Override
    public Boolean verifyAppointmentByOtp(Integer appointmentId, String otp) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppException("Appointment not found for given id.", HttpStatus.BAD_REQUEST));
        String databaseOtp = appointment.getOtpCode();
        if(databaseOtp==null){
            throw new AppException("Invalid request. Please try again", HttpStatus.BAD_REQUEST);
        }
        LocalTime otpGenTime = appointment.getOtpGenTime();
        LocalTime currentTime = LocalTime.now();

        long timeDifference = Duration.between(otpGenTime, currentTime).toSeconds();
        if(timeDifference > 1800){
            throw new AppException("OTP expired. Please try again", HttpStatus.BAD_REQUEST);
        }

        if(!Objects.equals(databaseOtp, otp)){
            throw new AppException("Invalid otp. Please try again", HttpStatus.BAD_REQUEST);
        }
        appointment.setStatus(AppointmentStatus.VERIFIED);
        appointment.setOtpCode(null);
        appointment.setOtpGenTime(null);
        appointmentRepository.save(appointment);
        return true;
    }

    @Override
    public IdSmsDto resendOTP(UpdateAppointmentApprovalDto approvalDto) {

        Appointment appointment = appointmentRepository.findById(approvalDto.getAppointmentId())
                .orElseThrow(()-> new AppException("Appointment not found for given id.", HttpStatus.BAD_REQUEST));
        PatientDetails patient = patientDetailsRepository.findById(approvalDto.getPatientId())
                .orElseThrow(()-> new AppException("Patient not found for given id", HttpStatus.BAD_REQUEST));
        if(!Objects.equals(appointment.getPatientDetails().getPatientDetailId(), approvalDto.getPatientId())
                || !Objects.equals(appointment.getDoctorDetails().getDoctorDetailId(), approvalDto.getDoctorId())){
            throw new AppException("Either Patient or Doctor is not associated with this appointment", HttpStatus.BAD_REQUEST);
        }

        if(appointment.getOtpCode()==null){
            throw new AppException("Invalid OTP request.", HttpStatus.BAD_REQUEST);
        }
        //send sms
//        String otp = RandomString.make(6);
        Random random = new Random();
        int otp = random.nextInt(9000) + 1000;

        String message = "Dear "+patient.getFirstName() +", Please use OTP: " +otp +" to confirm your appointment ";
        smsService.sendSms(patient.getPhoneNumber(), message);

        appointment.setOtpCode(String.valueOf(otp));
        appointment.setOtpGenTime(LocalTime.now());
        appointmentRepository.saveAndFlush(appointment);

        IdSmsDto idSmsDto = new IdSmsDto();
        idSmsDto.setOtp(message);
        idSmsDto.setId(appointment.getId());
        return idSmsDto;
    }

    @Override
    public List<Map<String, Object>> getAppointmentCount() {

        return appointmentRepository.listAppointmentCount();
    }
}