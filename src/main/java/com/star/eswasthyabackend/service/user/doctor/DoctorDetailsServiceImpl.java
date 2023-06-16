package com.star.eswasthyabackend.service.user.doctor;

import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailsRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
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

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new AppException("User not found for given user id.", HttpStatus.BAD_REQUEST));

        if(Boolean.FALSE.equals(user.getIsVerified())){
            throw new AppException("User account is not verified yet.", HttpStatus.BAD_REQUEST);
        }

        Integer count = doctorDetailsRepository.checkIfDataExists(requestDto.getUserId());
        if(count >= 1){
            throw new AppException("Doctor data already saved", HttpStatus.BAD_REQUEST);
        }

        DoctorDetails doctorDetails;
        if(requestDto.getDoctorDetailId() != null){
            doctorDetails = doctorDetailsRepository.findById(requestDto.getDoctorDetailId())
                    .orElseThrow(() -> new AppException("Doctor not found for given id", HttpStatus.BAD_REQUEST));
        }
        else{
            doctorDetails = new DoctorDetails();

        }

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
        doctorDetails.setUser(user);
        doctorDetailsRepository.saveAndFlush(doctorDetails);

        user.setIsFormFilled(true);

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
