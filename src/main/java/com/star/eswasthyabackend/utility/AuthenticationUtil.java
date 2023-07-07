package com.star.eswasthyabackend.utility;

import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.patient.PatientDetails;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import com.star.eswasthyabackend.repository.user.patient.PatientDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUtil {

    private final UserRepository userRepository;
    private final PatientDetailsRepository patientDetailsRepository;
    private final DoctorDetailsRepository doctorDetailsRepository;

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public Integer getUserId(){
        String username = getUserName();
        User user = userRepository.loadUserByUsername(username);
        return user.getId();
    }

    public Integer getPatientId(){
        String username = getUserName();
        PatientDetails patientDetail = patientDetailsRepository.loadPatientDetailByUsername(username);
        if(patientDetail == null){
            return null;
        }
        return patientDetail.getPatientDetailId();
    }

    public Integer getDoctorId(){
        String username = getUserName();
        DoctorDetails doctorDetail = doctorDetailsRepository.loadDoctorDetailByUserName(username);
        if(doctorDetail == null){
            return null;
        }
        return doctorDetail.getDoctorDetailId();
    }

    public String getRole(){
        String username = getUserName();
        return userRepository.loadRoleByUserName(username);
    }
}