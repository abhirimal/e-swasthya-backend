package com.star.eswasthyabackend.utility;

import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.repository.HospitalRepository;
import com.star.eswasthyabackend.repository.location.DistrictRepository;
import com.star.eswasthyabackend.repository.location.MunicipalityRepository;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.service.user.UserService;
import com.star.eswasthyabackend.service.user.doctor.DoctorDetailsService;
import com.star.eswasthyabackend.service.user.patient.PatientDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OneTimeDataLoader implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    private final DistrictRepository districtRepository;
    private final MunicipalityRepository municipalityRepository;
    private final HospitalRepository hospitalRepository;
    private final UserService userService;
    private final PatientDetailsServiceImpl patientDetailsService;
    private final DoctorDetailsService doctorDetailsService;
    private final UserRepository userRepository;

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
            userService.saveCustomUser(new User(1, "admin",null, "12345678", "admin.eswasthya@yopmail.com", true, true, "ADMIN"));

            //generate patients
            userService.saveCustomUser(new User(2, "Abhiyan","Rimal", "12345678",
                    "abhiyanrimal23@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(3, "Srijan","K.C", "12345678",
                    "srijan.kc@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(4, "Narendra","Chand", "12345678",
                    "narendra.chand@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(5, "Prabesh","Pokharel", "12345678",
                    "prabesh.pokharel@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(6, "Bimal","Gyawali", "12345678",
                    "bimal.gyawali@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(7, "Prakash", "Dhakal", "12345678",
                    "prakash.dhakal@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(8, "Santosh", "Thapa", "12345678",
                    "santosh.thapa@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(9, "Nirmala", "Bhandari", "12345678",
                    "nirmala.bhandari@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(10, "Kamala", "Shrestha", "12345678",
                    "kamala.shrestha@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(11, "Ganesh", "Gurung", "12345678",
                    "ganesh.gurung@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(12, "Sarita", "Bista", "12345678",
                    "sarita.bista@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(13, "Rajesh", "Maharjan", "12345678",
                    "rajesh.maharjan@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(14, "Anita", "Tamang", "12345678",
                    "anita.tamang@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(15, "Dipendra", "Poudel", "12345678",
                    "dipendra.poudel@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(16, "Rekha", "Rai", "12345678",
                    "rekha.rai@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(17, "Dilip", "Adhikari", "12345678",
                    "dilip.adhikari@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(18, "Pramila", "Koirala", "12345678",
                    "pramila.koirala@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(19, "Ramesh", "Basnet", "12345678",
                    "ramesh.basnet@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(20, "Shova", "Thakuri", "12345678",
                    "shova.thakuri@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(21, "Hari", "Rijal", "12345678",
                    "hari.rijal@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(22, "Sunita", "Lama", "12345678",
                    "sunita.lama@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(23, "Nabin", "Magar", "12345678",
                    "nabin.magar@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(24, "Sita", "Ghale", "12345678",
                    "sita.ghale@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(25, "Ram", "Budhathoki", "12345678",
                    "ram.budhathoki@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(26, "Gita", "Sharma", "12345678",
                    "gita.sharma@yopmail.com", true, true, "PATIENT"));


            //patient details
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(2, "51-808-213","9872102918", "70 kg",
                    "5.10 ft", "Male", "A+","xyz.jpeg", 65, "Chautari", "1993-06-05"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(3, "21-809-130","9872102918","65 kg",
                    "5.7 ft", "Male", "B+","xyz.jpeg", 120, "Galli Chowk", "1992-09-25"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(4, "89-129-123","9872102918","72 kg",
                    "6.1 ft", "Male", "O+","xyz.jpeg", 600, "Khola Chowk", "1995-01-14"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(5, "51-809-891","9872102918","69 kg",
                    "5.9 ft", "Male", "AB+","xyz.jpeg", 712, "Deurali", "1998-05-19"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(6, "48-921-892","9872102918","60 kg",
                    "5.11 ft", "Male", "A-","xyz.jpeg", 3, "Dobilla", "2000-08-28"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(7, "32-812-521", "9872102918", "68 kg",
                    "5.8 ft", "Male", "B-", "xyz.jpeg", 90, "Biratnagar", "1989-12-01"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(8, "42-981-315", "9872102918", "73 kg",
                    "6.2 ft", "Male", "O+", "xyz.jpeg", 450, "Tinkune", "1991-07-10"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(9, "67-102-572", "9872102918", "67 kg",
                    "5.7 ft", "Female", "AB-", "xyz.jpeg", 55, "Budhanilkantha", "1996-03-15"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(10, "19-425-918", "9872102918", "75 kg",
                    "6.0 ft", "Female", "A+", "xyz.jpeg", 320, "Maharajgunj", "1999-09-30"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(11, "56-819-632", "9872102918", "64 kg",
                    "5.6 ft", "Male", "O-", "xyz.jpeg", 12, "Baneshwor", "2001-11-21"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(12, "78-912-120", "9872102918", "70 kg",
                    "5.9 ft", "Female", "B+", "xyz.jpeg", 180, "Jhapa", "1987-08-07"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(13, "32-109-712", "9872102918", "68 kg",
                    "5.8 ft", "Male", "AB+", "xyz.jpeg", 42, "Dharan", "1990-02-11"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(14, "19-721-320", "9872102918", "73 kg",
                    "6.1 ft", "Female", "A-", "xyz.jpeg", 520, "Patan", "1994-06-24"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(15, "89-126-923", "9872102918", "66 kg",
                    "5.7 ft", "Male", "O+", "xyz.jpeg", 310, "Birgunj", "1997-01-10"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(16, "67-312-810", "9872102918", "72 kg",
                    "6.0 ft", "Female", "B-", "xyz.jpeg", 75, "Butwal", "2002-04-13"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(17, "21-817-402", "9872102918", "69 kg",
                    "5.9 ft", "Male", "AB+", "xyz.jpeg", 120, "Dhangadi", "1988-10-19"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(18, "48-921-310", "9872102918", "60 kg",
                    "5.11 ft", "Female", "O-", "xyz.jpeg", 23, "Surkhet", "1993-02-28"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(19, "73-812-730", "9872102918", "68 kg",
                    "5.8 ft", "Male", "A+", "xyz.jpeg", 87, "Janakpur", "1995-07-04"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(20, "37-210-931", "9872102918", "73 kg",
                    "6.2 ft", "Female", "B+", "xyz.jpeg", 600, "Hetauda", "1998-11-12"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(21, "54-321-814", "9872102918", "67 kg",
                    "5.7 ft", "Male", "AB-", "xyz.jpeg", 150, "Itahari", "1986-04-15"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(22, "89-430-917", "9872102918", "75 kg",
                    "6.0 ft", "Female", "O+", "xyz.jpeg", 250, "Ghorahi", "1992-09-30"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(23, "27-920-624", "9872102918", "64 kg",
                    "5.6 ft", "Male", "A-", "xyz.jpeg", 8, "Nepalgunj", "1996-03-03"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(24, "67-918-001", "9872102918", "70 kg",
                    "5.9 ft", "Female", "B+", "xyz.jpeg", 100, "Birtamod", "1985-08-27"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(25, "32-125-821", "9872102918", "68 kg",
                    "5.8 ft", "Male", "AB+", "xyz.jpeg", 35, "Lalitpur", "1991-02-10"));
            patientDetailsService.saveCustomData(new PatientDetailsRequestDto(26, "71-912-320", "9872102918", "73 kg",
                    "6.1 ft", "Female", "A-", "xyz.jpeg", 480, "Pokhara", "1994-06-02"));



            //generate doctors
            userService.saveCustomUser(new User(27, "Tracy","Shrestha", "12345678",
                    "tracy.shrestha@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(28, "Richa","Gurung", "12345678",
                    "richa.gurung@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(29, "Anurag","Baskota", "12345678",
                    "anurag.baskota@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(30, "Sunandan","Ghimire", "12345678",
                    "sunandan.ghimire@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(31, "Rishi","Marseni", "12345678",
                    "rishi.marseni@yopmail.com", true, true, "DOCTOR"));

        }
    }

    private void loadSqlScript(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String sqlScript = new String(bytes, StandardCharsets.UTF_8);
        jdbcTemplate.execute(sqlScript);
    }
}