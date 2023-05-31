package com.star.eswasthyabackend.service.user.doctor;

import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailsRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.user.User;
import com.star.eswasthyabackend.model.user.doctor.DoctorDetails;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DoctorDetailsServiceImpl implements DoctorDetailsService {

    private final DoctorDetailsRepository doctorDetailsRepository;
    private final UserRepository userRepository;

    @Override
    public Integer saveDoctorDetails(DoctorDetailsRequestDto requestDto) {

        DoctorDetails doctorDetails;
        if(requestDto.getDoctorDetailId() != null){
            doctorDetails = doctorDetailsRepository.findById(requestDto.getDoctorDetailId())
                    .orElseThrow(() -> new AppException("Doctor not found for given id", HttpStatus.BAD_REQUEST));
        }
        else{
            doctorDetails = new DoctorDetails();

        }

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new AppException("User not found for given user id.", HttpStatus.BAD_REQUEST));

        doctorDetails.setFirstName(user.getFirstName());
        doctorDetails.setLastName(user.getLastName());
        doctorDetails.setEmail(user.getEmail());
        doctorDetails.setPhoneNumber(requestDto.getPhoneNumber());
        doctorDetails.setNmcLicenseNo(requestDto.getNmcLicenseNo());
        doctorDetails.setSpecialization(requestDto.getSpecialization());
        doctorDetails.setExperience(requestDto.getExperience());
        doctorDetails.setEducation(requestDto.getEducation());
        doctorDetails.setGender(requestDto.getGender());
        doctorDetails.setAssociatedHospital(requestDto.getAssociatedHospital());
        doctorDetails.setLocation(requestDto.getLocation());
        doctorDetailsRepository.saveAndFlush(doctorDetails);

        return doctorDetails.getDoctorDetailId();
    }

    @Override
    public Map<String, Object> findById(Integer id) {
        return doctorDetailsRepository.findDoctorDetailsById(id);
    }

    @Override
    public List<Map<String, Object>> listAllDoctor() {
        return doctorDetailsRepository.listAllDoctor();
    }


}
