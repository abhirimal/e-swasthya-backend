package com.star.eswasthyabackend.service.user.doctor;

import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailsRequestDto;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.model.Hospital;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.model.doctor.DoctorDetails;
import com.star.eswasthyabackend.model.location.District;
import com.star.eswasthyabackend.model.location.Location;
import com.star.eswasthyabackend.model.location.Municipality;
import com.star.eswasthyabackend.repository.HospitalRepository;
import com.star.eswasthyabackend.repository.location.DistrictRepository;
import com.star.eswasthyabackend.repository.location.LocationRepository;
import com.star.eswasthyabackend.repository.location.MunicipalityRepository;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.repository.user.doctor.DoctorDetailsRepository;
import com.star.eswasthyabackend.utility.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DoctorDetailsServiceImpl implements DoctorDetailsService {

    private final DoctorDetailsRepository doctorDetailsRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;
    private final DistrictRepository districtRepository;
    private final MunicipalityRepository municipalityRepository;
    private final HospitalRepository hospitalRepository;
    private final JWTUtil jwtUtil;

    @Override
    public String saveDoctorDetails(DoctorDetailsRequestDto requestDto) {

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new AppException("User not found for given user id.", HttpStatus.BAD_REQUEST));

        if(Boolean.FALSE.equals(user.getIsVerified())){
            throw new AppException("User account is not verified yet.", HttpStatus.BAD_REQUEST);
        }

        if(!Objects.equals(user.getRole(), "DOCTOR")){
            throw new AppException("Invalid request. Please try again.", HttpStatus.BAD_REQUEST);
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
        //location
        Location location = new Location();
        Integer districtId = districtRepository.findDistrictIdByMunicipalityId(requestDto.getMunicipalityId());
        District district = districtRepository.findById(districtId)
                .orElseThrow(()-> new AppException("District not found for given district id", HttpStatus.BAD_REQUEST));
        Municipality municipality = municipalityRepository.findById(requestDto.getMunicipalityId())
                .orElseThrow(()-> new AppException("Municipality not found for given municipality id.", HttpStatus.BAD_REQUEST));
        location.setDistrict(district);
        location.setMunicipality(municipality);
        location.setStreetAddress(requestDto.getStreetAddress());
        location = locationRepository.saveAndFlush(location);
        doctorDetails.setLocation(location);

        List<Hospital> hospitalList = hospitalRepository.findAllById(requestDto.getAssociatedHospitalIdList());
        doctorDetails.setAssociatedHospitalList(hospitalList);

        doctorDetails.setUser(user);
        doctorDetailsRepository.saveAndFlush(doctorDetails);

        user.setIsFormFilled(true);
        userRepository.save(user);

        return jwtUtil.generateNewToken();
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
