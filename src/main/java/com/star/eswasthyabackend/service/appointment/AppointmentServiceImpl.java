package com.star.eswasthyabackend.service.appointment;

import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;
import com.star.eswasthyabackend.dto.appointment.UpdateAppointmentApprovalDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.Appointment;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.AppointmentRepository;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    private final PatientDetailsRepository patientDetailsRepository;

    private final DoctorDetailsRepository doctorDetailsRepository;
    private final SmsService smsService;

    @Override
    @Transactional
    public String save(AppointmentRequest appointmentRequest) {

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
        appointment.setIsApproved(false);
        appointment.setIsActive(false);
        appointment.setIsVerifiedBySms(false);

        //send sms
        String otp = RandomString.make(6);
        String message = "Dear "+patient.getFirstName() +", Please use OTP: " +otp +" to confirm your appointment ";
        smsService.sendSms(patient.getPhoneNumber(), message);

        appointment.setOtpCode(otp);
        appointment.setOtpGenTime(LocalTime.now());
        appointmentRepository.saveAndFlush(appointment);
        return message;
    }

    @Override
    public Map<String, Object> viewById(Integer appointmentId) {
        return appointmentRepository.viewById(appointmentId);
    }

    @Override
    public List<Map<String, Object>> viewByDoctorId(Integer doctorId) {
        return appointmentRepository.viewByDoctorId(doctorId);
    }

    @Override
    public List<Map<String, Object>> viewByPatientId(Integer patientId) {
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

    @Override
    public Boolean updateAppointmentApproval(UpdateAppointmentApprovalDto approvalDto) {
        Appointment appointment = appointmentRepository.findById(approvalDto.getAppointmentId())
                .orElseThrow(()-> new AppException("Appointment not found for given id.", HttpStatus.BAD_REQUEST));

        if(!Objects.equals(appointment.getPatientDetails().getPatientDetailId(), approvalDto.getPatientId())
        || !Objects.equals(appointment.getDoctorDetails().getDoctorDetailId(), approvalDto.getDoctorId())){
            throw new AppException("Either Patient or Doctor is not associated with this appointment", HttpStatus.BAD_REQUEST);
        }

        appointment.setIsApproved(approvalDto.getIsApproved());
        appointment.setIsActive(true);
        appointmentRepository.save(appointment);
        return true;
    }

    @Override
    public Boolean verifyAppointmentByOtp(Integer appointmentId, String otp) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new AppException("Appointment not found for given id.", HttpStatus.BAD_REQUEST));
        String databaseOtp = appointment.getOtpCode();
        LocalTime otpGenTime = appointment.getOtpGenTime();
        LocalTime currentTime = LocalTime.now();

        long timeDifference = Duration.between(otpGenTime, currentTime).toSeconds();
        if(timeDifference > 1800){
            throw new AppException("OTP expired. Please try again", HttpStatus.BAD_REQUEST);
        }

        if(!Objects.equals(databaseOtp, otp)){
            throw new AppException("OTP verified successfully.", HttpStatus.BAD_REQUEST);
        }
        appointment.setIsVerifiedBySms(true);
        appointmentRepository.save(appointment);
        return true;
    }
}