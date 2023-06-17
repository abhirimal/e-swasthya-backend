package com.star.eswasthyabackend.utility;

import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.repository.HospitalRepository;
import com.star.eswasthyabackend.repository.location.DistrictRepository;
import com.star.eswasthyabackend.repository.location.MunicipalityRepository;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class OneTimeDataLoader implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    private final DistrictRepository districtRepository;
    private final MunicipalityRepository municipalityRepository;
    private final HospitalRepository hospitalRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    public OneTimeDataLoader(JdbcTemplate jdbcTemplate, DistrictRepository districtRepository, MunicipalityRepository municipalityRepository, HospitalRepository hospitalRepository, UserService userService, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.districtRepository = districtRepository;
        this.municipalityRepository = municipalityRepository;
        this.hospitalRepository = hospitalRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Integer countDistrict = districtRepository.countDistrict();

        if(countDistrict == 0){
            loadSqlScript("districts.sql");
        }

        Integer countMunicipality = municipalityRepository.countMunicipality();
        if(countMunicipality == 0){
            loadSqlScript("municipality.sql");
        }

        Integer countHospital = hospitalRepository.countHospital();
        if(countHospital == 0){
            loadSqlScript("hospitals.sql");
        }

        //generate users
        Integer countUser = userRepository.countUser();
        if(countUser == 0){
            //generate admin
            userService.saveCustomAdmin(new User(1, "admin",null, "12345678", "admin.eswasthya@yopmail.com", true, true, "ADMIN"));

            //generate patients
            userService.saveCustomAdmin(new User(2, "Abhiyan","Rimal", "12345678", "abhiyanrimal23@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomAdmin(new User(3, "Srijan","K.C", "12345678", "srijan.kc@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomAdmin(new User(4, "Narendra","Chand", "12345678", "narendra.chand@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomAdmin(new User(5, "Prabesh","Pokharel", "12345678", "prabesh.pokharel@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomAdmin(new User(6, "Bimal","Gyawali", "12345678", "bimal.gyawali@yopmail.com", true, true, "PATIENT"));


            //generate doctors
            userService.saveCustomAdmin(new User(7, "Tracy","Shrestha", "12345678", "tracy.shrestha@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomAdmin(new User(8, "Richa","Gurung", "12345678", "richa.gurung@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomAdmin(new User(9, "Anurag","Baskota", "12345678", "anurag.baskota@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomAdmin(new User(10, "Sunandan","Ghimire", "12345678", "sunandan.ghimire@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomAdmin(new User(11, "Rishi","Marseni", "12345678", "rishi.marseni@yopmail.com", true, true, "DOCTOR"));

        }
    }

    private void loadSqlScript(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String sqlScript = new String(bytes, StandardCharsets.UTF_8);
        jdbcTemplate.execute(sqlScript);
    }
}