package com.star.eswasthyabackend.utility;

import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailsRequestDto;
import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.repository.HospitalRepository;
import com.star.eswasthyabackend.repository.location.DistrictRepository;
import com.star.eswasthyabackend.repository.location.MunicipalityRepository;
import com.star.eswasthyabackend.repository.prescription.MedicineRepository;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.repository.vaccination.VaccinationRepository;
import com.star.eswasthyabackend.repository.vaccination.VaccineRepository;
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
import java.util.Arrays;

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
    private final VaccineRepository vaccineRepository;
    private final MedicineRepository medicineRepository;

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

        Integer countVaccine = vaccineRepository.countVaccine();
        if(countVaccine == 0){
            loadSqlScript("vaccine.sql");
        }

        Integer countMedicine = medicineRepository.countMedicine();
        if(countMedicine == 0){
            loadSqlScript("medicine.sql");
        }

        //generate users
        Integer countUser = userRepository.countUser();
        if(countUser == 0){
            //generate admin
            userService.saveCustomUser(new User(1, "admin",null, "12345678",
                    "admin.eswasthya@yopmail.com", true, true, "ADMIN"));

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
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(2, "51-808-213","9872102918", "70 kg",
                    "5.10 ft", "Male", "A+","xyz.jpeg", 65, "Chautari", "1993-06-05"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(3, "21-809-130","9872102918","65 kg",
                    "5.7 ft", "Male", "B+","xyz.jpeg", 120, "Galli Chowk", "1992-09-25"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(4, "89-129-123","9872102918","72 kg",
                    "6.1 ft", "Male", "O+","xyz.jpeg", 600, "Khola Chowk", "1995-01-14"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(5, "51-809-891","9872102918","69 kg",
                    "5.9 ft", "Male", "AB+","xyz.jpeg", 712, "Deurali", "1998-05-19"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(6, "48-921-892","9872102918","60 kg",
                    "5.11 ft", "Male", "A-","xyz.jpeg", 3, "Dobilla", "2000-08-28"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(7, "32-812-521", "9872102918", "68 kg",
                    "5.8 ft", "Male", "B-", "xyz.jpeg", 90, "Thapa Gaun", "1989-12-01"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(8, "42-981-315", "9872102918", "73 kg",
                    "6.2 ft", "Male", "O+", "xyz.jpeg", 450, "Shiva Mandir", "1991-07-10"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(9, "67-102-572", "9872102918", "67 kg",
                    "5.7 ft", "Female", "AB-", "xyz.jpeg", 55, "Thuli Gaun", "1996-03-15"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(10, "19-425-918", "9872102918", "75 kg",
                    "6.0 ft", "Female", "A+", "xyz.jpeg", 320, "Town", "1999-09-30"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(11, "56-819-632", "9872102918", "64 kg",
                    "5.6 ft", "Male", "O-", "xyz.jpeg", 12, "Pakaudi", "2001-11-21"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(12, "78-912-120", "9872102918", "70 kg",
                    "5.9 ft", "Female", "B+", "xyz.jpeg", 180, "Batulchowr", "1987-08-07"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(13, "32-109-712", "9872102918", "68 kg",
                    "5.8 ft", "Male", "AB+", "xyz.jpeg", 42, "Lamachowr", "1990-02-11"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(14, "19-721-320", "9872102918", "73 kg",
                    "6.1 ft", "Female", "A-", "xyz.jpeg", 520, "Shrijana Nagar", "1994-06-24"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(15, "89-126-923", "9872102918", "66 kg",
                    "5.7 ft", "Male", "O+", "xyz.jpeg", 310, "Pipal Bot", "1997-01-10"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(16, "67-312-810", "9872102918", "72 kg",
                    "6.0 ft", "Female", "B-", "xyz.jpeg", 75, "Dallu", "2002-04-13"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(17, "21-817-402", "9872102918", "69 kg",
                    "5.9 ft", "Male", "AB+", "xyz.jpeg", 120, "Dhaneswor", "1988-10-19"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(18, "48-921-310", "9872102918", "60 kg",
                    "5.11 ft", "Female", "O-", "xyz.jpeg", 23, "Diyalo Tole", "1993-02-28"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(19, "73-812-730", "9872102918", "68 kg",
                    "5.8 ft", "Male", "A+", "xyz.jpeg", 87, "Gangate", "1995-07-04"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(20, "37-210-931", "9872102918", "73 kg",
                    "6.2 ft", "Female", "B+", "xyz.jpeg", 600, "Pakuwa", "1998-11-12"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(21, "54-321-814", "9872102918", "67 kg",
                    "5.7 ft", "Male", "AB-", "xyz.jpeg", 150, "Silmi", "1986-04-15"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(22, "89-430-917", "9872102918", "75 kg",
                    "6.0 ft", "Female", "O+", "xyz.jpeg", 250, "Chautari", "1992-09-30"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(23, "27-920-624", "9872102918", "64 kg",
                    "5.6 ft", "Male", "A-", "xyz.jpeg", 8, "Dhampus Chowk", "1996-03-03"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(24, "67-918-001", "9872102918", "70 kg",
                    "5.9 ft", "Female", "B+", "xyz.jpeg", 100, "Bhatkeko Pool", "1985-08-27"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(25, "32-125-821", "9872102918", "68 kg",
                    "5.8 ft", "Male", "AB+", "xyz.jpeg", 35, "Naya Bazar", "1991-02-10"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(26, "71-912-320", "9872102918", "73 kg",
                    "6.1 ft", "Female", "A-", "xyz.jpeg", 480, "Kalimati", "1994-06-02"));


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
            userService.saveCustomUser(new User(32, "Nisha", "Shakya", "12345678",
                    "nisha.shakya@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(33, "Aashish", "Rajbanshi", "12345678",
                    "aashish.rajbanshi@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(34, "Bikash", "Sharma", "12345678",
                    "bikash.sharma@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(35, "Sita", "Tiwari", "12345678",
                    "sita.tiwari@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(36, "Prakash", "Tamang", "12345678",
                    "prakash.tamang@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(37, "Manoj", "Chaudhary", "12345678",
                    "manoj.chaudhary@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(38, "Komal", "Bhattarai", "12345678",
                    "komal.bhattarai@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(39, "Priyanka", "Pande", "12345678",
                    "priyanka.pande@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(40, "Rajesh", "Rai", "12345678",
                    "rajesh.rai@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(41, "Ravi", "Singh", "12345678",
                    "ravi.singh@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(42, "Saraswati", "Gupta", "12345678",
                    "saraswati.gupta@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(43, "Dinesh", "Yadav", "12345678",
                    "dinesh.yadav@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(44, "Suman", "Adhikari", "12345678",
                    "suman.adhikari@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(45, "Sunita", "Pande", "12345678",
                    "sunita.pande@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(46, "Amit", "Chaudhary", "12345678",
                    "amit.chaudhary@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(47, "Sanjay", "Shrestha", "12345678",
                    "sanjay.shrestha@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(48, "Deepika", "Gurung", "12345678",
                    "deepika.gurung@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(49, "Shiva", "Bhatt", "12345678",
                    "shiva.bhatt@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(50, "Ashish", "Thapa", "12345678",
                    "ashish.thapa@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(51, "Aakash", "Shrestha", "12345678",
                    "aakash.shrestha@yopmail.com", true, true, "DOCTOR"));

            //doctor details
            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(27, "7891",
                    "9810298716", "MBBS", "Cardiologist", "5 years",
                    "Male", Arrays.asList(1, 2, 3), "Sano Gaun", 87, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(28, "6923",
                    "9812309764", "MD", "Dermatologist", "8 years",
                    "Male", Arrays.asList(4, 7), "Gaida Chowk", 152, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(29, "4321",
                    "9845678901", "MS", "Gastroenterologist", "12 years",
                    "Female", Arrays.asList(2, 3, 6), "Rampur", 326, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(30, "9823",
                    "9867543210", "MBBS", "Neurologist", "15 years",
                    "Male", Arrays.asList(1, 3, 9), "Chagal", 499, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(31, "7410",
                    "9856784321", "MD", "Obstetrician/Gynecologist", "10 years",
                    "Female", Arrays.asList(5, 8), "Parbati Chowk", 245, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(32, "2678",
                    "9810765432", "MS", "Orthopedic Surgeon", "6 years",
                    "Male", Arrays.asList(1, 4), "Gokarna", 89, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(33, "5901",
                    "9841098765", "MBBS", "Pediatrician", "3 years",
                    "Female", Arrays.asList(2, 7, 9), "Purano Bazar", 138, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(34, "1324",
                    "9812345670", "MD", "Naya buspark", "18 years",
                    "Male", Arrays.asList(3, 5), "Balkumari", 287, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(35, "4680",
                    "9865432109", "MS", "Radiologist", "9 years",
                    "Female", Arrays.asList(1, 6, 8), "Sano Bharyang", 212, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(36, "8157",
                    "9810987654", "MBBS", "Urologist", "14 years",
                    "Male", Arrays.asList(4, 9), "Soltimode", 68, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(37, "5790",
                    "9847654321", "MD", "Cardiologist", "7 years",
                    "Female", Arrays.asList(2, 3, 5), "Purano Buspark", 183, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(38, "6784",
                    "9812309876", "MS", "Dermatologist", "11 years",
                    "Male", Arrays.asList(1, 6, 7), "Naya Galli", 410, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(39, "4203",
                    "9845678902", "MBBS", "Gastroenterologist", "16 years",
                    "Female", Arrays.asList(3, 4), "Hanuman Chowk", 74, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(40, "8752",
                    "9867543219", "MD", "Neurologist", "13 years",
                    "Male", Arrays.asList(2, 5, 9), "Naadi Pur", 625, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(41, "1468",
                    "9856784327", "MS", "Obstetrician/Gynecologist", "8 years",
                    "Female", Arrays.asList(1, 3, 7), "Bagar", 368, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(42, "6512",
                    "9810765438", "MBBS", "Orthopedic Surgeon", "5 years",
                    "Male", Arrays.asList(4, 6), "Tudikhel", 162, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(43, "2109",
                    "9841098766", "MD", "Pediatrician", "10 years",
                    "Female", Arrays.asList(1, 2, 9), "Gadesh mandir", 550, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(44, "4873",
                    "9812345671", "MS", "Psychiatrist", "19 years",
                    "Male", Arrays.asList(3, 4), "Chauni Gate", 715, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(45, "7635",
                    "9865432108", "MBBS", "Radiologist", "7 years",
                    "Female", Arrays.asList(2, 5, 8), "Jalapa Chowk", 397, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(46, "9034",
                    "9810987652", "MD", "Urologist", "18 years",
                    "Male", Arrays.asList(1, 6), "Manamaiju", 645, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(47, "3076",
                    "9847654320", "MS", "Cardiologist", "10 years",
                    "Female", Arrays.asList(3, 7), "Naag Pokhari", 589, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(48, "8342",
                    "9812309875", "MBBS", "Dermatologist", "14 years",
                    "Male", Arrays.asList(2, 4, 9), "Bhalu danda", 194, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(49, "2938",
                    "9845678903", "MD", "Gastroenterologist", "11 years",
                    "Female", Arrays.asList(1, 5), "Janak chowk", 128, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(50, "7361",
                    "9867543218", "MS", "Neurologist", "17 years",
                    "Male", Arrays.asList(3, 6, 8), "Sanepa", 675, "xyz.jpeg"));

            doctorDetailsService.saveDoctorDetails(new DoctorDetailsRequestDto(51, "9152",
                    "9856784325", "MBBS", "Obstetrician/Gynecologist", "9 years",
                    "Female", Arrays.asList(1, 2, 7), "Purano chautari", 97, "xyz.jpeg"));
        }
    }

    private void loadSqlScript(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String sqlScript = new String(bytes, StandardCharsets.UTF_8);
        jdbcTemplate.execute(sqlScript);
    }
}