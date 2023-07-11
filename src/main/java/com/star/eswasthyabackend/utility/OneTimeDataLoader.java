package com.star.eswasthyabackend.utility;

import com.star.eswasthyabackend.dto.appointment.AppointmentRequest;
import com.star.eswasthyabackend.dto.diagnosis.DiagnosisRequestDto;
import com.star.eswasthyabackend.dto.prescription.PrescriptionRequestDto;
import com.star.eswasthyabackend.dto.testResult.TestResultRequestDto;
import com.star.eswasthyabackend.dto.user.doctor.DoctorDetailsRequestDto;
import com.star.eswasthyabackend.dto.user.patient.PatientDetailsRequestDto;
import com.star.eswasthyabackend.dto.vaccination.VaccinationRequest;
import com.star.eswasthyabackend.model.TestResult;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.repository.HospitalRepository;
import com.star.eswasthyabackend.repository.diagnosis.DiseaseRepository;
import com.star.eswasthyabackend.repository.location.DistrictRepository;
import com.star.eswasthyabackend.repository.location.MunicipalityRepository;
import com.star.eswasthyabackend.repository.prescription.MedicineRepository;
import com.star.eswasthyabackend.repository.user.UserRepository;
import com.star.eswasthyabackend.repository.vaccination.VaccinationRepository;
import com.star.eswasthyabackend.repository.vaccination.VaccineRepository;
import com.star.eswasthyabackend.service.appointment.AppointmentService;
import com.star.eswasthyabackend.service.diagnosis.DiagnosisService;
import com.star.eswasthyabackend.service.prescription.PrescriptionService;
import com.star.eswasthyabackend.service.testResult.TestResultService;
import com.star.eswasthyabackend.service.user.UserService;
import com.star.eswasthyabackend.service.user.doctor.DoctorDetailsService;
import com.star.eswasthyabackend.service.user.patient.PatientDetailsServiceImpl;
import com.star.eswasthyabackend.service.vaccination.VaccinationService;
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
    private final DiseaseRepository diseaseRepository;
    private final AppointmentService appointmentService;
    private final VaccinationService vaccinationService;
    private final DiagnosisService diagnosisService;
    private final TestResultService testResultService;
    private final PrescriptionService prescriptionService;

    @Override
    public void run(String... args) throws Exception {

        Integer countDistrict = districtRepository.countDistrict();

        if (countDistrict == 0) {
            loadSqlScript("districts.sql");
        }

        Integer countMunicipality = municipalityRepository.countMunicipality();
        if (countMunicipality == 0) {
            loadSqlScript("municipality.sql");
        }

        Integer countHospital = hospitalRepository.countHospital();
        if (countHospital == 0) {
            loadSqlScript("hospitals.sql");
        }

        Integer countVaccine = vaccineRepository.countVaccine();
        if (countVaccine == 0) {
            loadSqlScript("vaccine.sql");
        }

        Integer countMedicine = medicineRepository.countMedicine();
        if (countMedicine == 0) {
            loadSqlScript("medicine.sql");
        }

        Integer countDisease = diseaseRepository.countDisease();
        if (countDisease == 0) {
            loadSqlScript("disease.sql");
        }

        //generate users
        Integer countUser = userRepository.countUser();
        if (countUser == 0) {
            //generate admin
            userService.saveCustomUser(new User(1, "admin", null, "12345678",
                    "admin.eswasthya@yopmail.com", true, true, "ADMIN"));

            //generate patients
            userService.saveCustomUser(new User(2, "Abhiyan", "Rimal", "12345678",
                    "abhiyanrimal23@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(3, "Srijan", "K.C", "12345678",
                    "srijan.kc@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(4, "Narendra", "Chand", "12345678",
                    "narendra.chand@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(5, "Prabesh", "Pokharel", "12345678",
                    "prabesh.pokharel@yopmail.com", true, true, "PATIENT"));
            userService.saveCustomUser(new User(6, "Bimal", "Gyawali", "12345678",
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
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(2, "51-808-213", "9817898901", "70",
                    "5.10", "Male", "A+", "xyz.jpeg", 587, "Chautari", "1993-06-05"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(3, "21-809-130", "9889201928", "65",
                    "5.7", "Male", "B+", "xyz.jpeg", 587, "Galli Chowk", "1992-09-25"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(4, "89-129-123", "9899901829", "72",
                    "6.1", "Male", "O+", "xyz.jpeg", 589, "Khola Chowk", "1995-01-14"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(5, "51-809-891", "9810019218", "69",
                    "5.9", "Male", "AB+", "xyz.jpeg", 591, "Deurali", "1998-05-19"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(6, "48-921-892", "9192872892", "60",
                    "5.11", "Male", "A-", "xyz.jpeg", 591, "Dobilla", "2000-08-28"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(7, "32-812-521", "9881920192", "68",
                    "5.8", "Male", "B-", "xyz.jpeg", 596, "Thapa Gaun", "1989-12-01"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(8, "42-981-315", "9992194732", "73",
                    "6.2", "Male", "O+", "xyz.jpeg", 615, "Shiva Mandir", "1991-07-10"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(9, "67-102-572", "9800000012", "67",
                    "5.7", "Female", "AB-", "xyz.jpeg", 609, "Thuli Gaun", "1996-03-15"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(10, "19-425-918", "9301919191", "75",
                    "6.0", "Female", "A+", "xyz.jpeg", 608, "Town", "1999-09-30"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(11, "56-819-632", "9010101010", "64",
                    "5.6", "Male", "O-", "xyz.jpeg", 623, "Pakaudi", "2001-11-21"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(12, "78-912-120", "9810921212", "70",
                    "5.9", "Female", "B+", "xyz.jpeg", 624, "Batulchowr", "1987-08-07"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(13, "32-109-712", "9000000021", "68",
                    "5.8", "Male", "AB+", "xyz.jpeg", 628, "Lamachowr", "1990-02-11"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(14, "19-721-320", "9899089800", "73",
                    "6.1", "Female", "A-", "xyz.jpeg", 631, "Shrijana Nagar", "1994-06-24"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(15, "89-126-923", "9811111190", "66",
                    "5.7", "Male", "O+", "xyz.jpeg", 326, "Pipal Bot", "1997-01-10"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(16, "67-312-810", "9811111111", "72",
                    "6.0", "Female", "B-", "xyz.jpeg", 326, "Dallu", "2002-04-13"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(17, "21-817-402", "9833333300", "69",
                    "5.9", "Male", "AB+", "xyz.jpeg", 330, "Dhaneswor", "1988-10-19"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(18, "48-921-310", "9889891010", "60",
                    "5.11", "Female", "O-", "xyz.jpeg", 331, "Diyalo Tole", "1993-02-28"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(19, "73-812-730", "9899999999", "68",
                    "5.8", "Male", "A+", "xyz.jpeg", 335, "Gangate", "1995-07-04"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(20, "37-210-931", "9800000000", "73",
                    "6.2", "Female", "B+", "xyz.jpeg", 337, "Pakuwa", "1998-11-12"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(21, "54-321-814", "9804040404", "67",
                    "5.7", "Male", "AB-", "xyz.jpeg", 328, "Silmi", "1986-04-15"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(22, "89-430-917", "9818181818", "75",
                    "6.0", "Female", "O+", "xyz.jpeg", 340, "Chautari", "1992-09-30"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(23, "27-920-624", "9870707070", "64",
                    "5.6", "Male", "A-", "xyz.jpeg", 343, "Dhampus Chowk", "1996-03-03"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(24, "67-918-001", "9891919191", "70",
                    "5.9", "Female", "B+", "xyz.jpeg", 386, "Bhatkeko Pool", "1985-08-27"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(25, "32-125-821", "9834534534", "68",
                    "5.8", "Male", "AB+", "xyz.jpeg", 391, "Naya Bazar", "1991-02-10"));
            patientDetailsService.savePatientDetails(new PatientDetailsRequestDto(26, "71-912-320", "9898198197", "73",
                    "6.1", "Female", "A-", "xyz.jpeg", 343, "Kalimati", "1994-06-02"));


            //generate doctors
            userService.saveCustomUser(new User(27, "Tracy", "Shrestha", "12345678",
                    "tracy.shrestha@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(28, "Richa", "Gurung", "12345678",
                    "richa.gurung@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(29, "Anurag", "Baskota", "12345678",
                    "anurag.baskota@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(30, "Sunandan", "Ghimire", "12345678",
                    "sunandan.ghimire@yopmail.com", true, true, "DOCTOR"));
            userService.saveCustomUser(new User(31, "Rishi", "Marseni", "12345678",
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

            //save appointment data
            appointmentService.save(new AppointmentRequest(1, "Annual Physical Exam", "ACCEPTED", 30, 4, 5));
            appointmentService.save(new AppointmentRequest(2, "Dental Cleaning", "ACCEPTED", 33, 5, 3));
            appointmentService.save(new AppointmentRequest(3, "Eye Checkup", "ACCEPTED", 29, 6, 7));
            appointmentService.save(new AppointmentRequest(4, "Blood Test", "ACCEPTED", 31, 7, 1));
            appointmentService.save(new AppointmentRequest(5, "Minor Surgery", "ACCEPTED", 35, 8, 4));
            appointmentService.save(new AppointmentRequest(6, "Flu Vaccination", "ACCEPTED", 27, 9, 6));
            appointmentService.save(new AppointmentRequest(7, "Follow-up Consultation", "ACCEPTED", 32, 10, 8));
            appointmentService.save(new AppointmentRequest(8, "Physical Therapy Session", "ACCEPTED", 34, 11, 2));
            appointmentService.save(new AppointmentRequest(9, "Cardiac Checkup", "ACCEPTED", 28, 12, 9));
            appointmentService.save(new AppointmentRequest(10, "Pediatric Consultation", "ACCEPTED", 30, 13, 5));
            appointmentService.save(new AppointmentRequest(11, "Dermatology Appointment", "ACCEPTED", 33, 14, 3));
            appointmentService.save(new AppointmentRequest(12, "Chiropractic Session", "ACCEPTED", 29, 15, 7));
            appointmentService.save(new AppointmentRequest(13, "X-ray Imaging", "ACCEPTED", 31, 16, 1));
            appointmentService.save(new AppointmentRequest(14, "Orthopedic Consultation", "ACCEPTED", 35, 17, 4));
            appointmentService.save(new AppointmentRequest(15, "Allergy Testing", "ACCEPTED", 27, 18, 6));
            appointmentService.save(new AppointmentRequest(16, "Gastroenterology Appointment", "ACCEPTED", 32, 19, 8));
            appointmentService.save(new AppointmentRequest(17, "Psychiatry Session", "ACCEPTED", 34, 20, 2));
            appointmentService.save(new AppointmentRequest(18, "Neurology Consultation", "ACCEPTED", 28, 21, 9));
            appointmentService.save(new AppointmentRequest(19, "ENT Checkup", "ACCEPTED", 30, 22, 5));
            appointmentService.save(new AppointmentRequest(10, "Prenatal Visit", "ACCEPTED", 33, 23, 3));
            appointmentService.save(new AppointmentRequest(21, "Urology Appointment", "ACCEPTED", 29, 24, 7));
            appointmentService.save(new AppointmentRequest(22, "Ophthalmology Consultation", "ACCEPTED", 31, 25, 1));
            //update appointment set status='ACCEPTED'


            //save vaccination data
            vaccinationService.saveVaccinationReport(new VaccinationRequest(1, "Polio", "2015-03-23", 1, 2));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(2, "Moderna", "2018-06-12", 1, 3));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(3, "Hepatitis B", "2016-09-05", 1, 4));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(4, "Pfizer", "2019-11-18", 1, 5));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(5, "Rabies", "2020-02-29", 1, 6));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(6, "Hepatitis A", "2017-07-21", 1, 7));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(7, "Polio", "2014-12-10", 1, 8));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(8, "Moderna", "2021-05-07", 1, 9));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(9, "Hepatitis B", "2018-08-16", 1, 10));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(10, "Pfizer", "2022-01-25", 1, 11));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(11, "Rabies", "2023-04-19", 1, 12));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(12, "Hepatitis A", "2020-09-03", 1, 13));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(13, "Polio", "2017-12-05", 1, 14));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(14, "Moderna", "2019-03-28", 1, 15));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(15, "Hepatitis B", "2018-04-10", 1, 16));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(16, "Pfizer", "2020-07-15", 1, 17));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(17, "Rabies", "2021-09-27", 1, 18));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(18, "Hepatitis A", "2019-02-14", 1, 19));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(19, "Polio", "2016-08-02", 1, 20));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(20, "Moderna", "2022-03-19", 1, 21));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(21, "Hepatitis B", "2019-06-11", 1, 22));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(22, "Pfizer", "2023-08-07", 1, 23));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(23, "Rabies", "2021-11-03", 1, 24));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(24, "Hepatitis A", "2021-04-02", 1, 25));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(25, "Polio", "2018-09-20", 1, 13));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(26, "Moderna", "2020-11-09", 1, 2));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(27, "Hepatitis B", "2021-01-14", 1, 3));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(28, "Pfizer", "2019-06-27", 1, 4));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(29, "Rabies", "2022-03-05", 1, 5));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(30, "Hepatitis A", "2023-01-31", 1, 6));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(31, "Polio", "2020-10-07", 1, 7));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(32, "Moderna", "2018-12-22", 1, 8));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(33, "Hepatitis B", "2016-06-16", 1, 9));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(34, "Pfizer", "2017-09-03", 1, 10));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(35, "Rabies", "2021-08-18", 1, 11));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(36, "Hepatitis A", "2023-02-15", 1, 12));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(37, "Polio", "2015-07-11", 1, 13));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(38, "Moderna", "2019-05-29", 1, 14));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(39, "Hepatitis B", "2020-08-24", 1, 15));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(40, "Pfizer", "2022-11-01", 1, 16));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(41, "Rabies", "2018-03-07", 1, 17));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(42, "Hepatitis A", "2021-06-30", 1, 18));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(43, "Polio", "2021-01-23", 1, 19));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(44, "Moderna", "2022-10-09", 1, 23));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(45, "Hepatitis B", "2023-04-11", 1, 20));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(46, "Pfizer", "2022-09-07", 1, 21));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(47, "Rabies", "2021-06-18", 1, 22));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(48, "Hepatitis A", "2023-10-24", 1, 23));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(49, "Polio", "2021-03-14", 1, 24));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(50, "Moderna", "2022-05-05", 1, 25));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(51, "Hepatitis B", "2022-07-17", 1, 14));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(52, "Pfizer", "2021-08-22", 1, 2));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(53, "Rabies", "2022-02-09", 1, 3));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(54, "Hepatitis A", "2022-12-06", 1, 4));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(55, "Polio", "2020-05-27", 1, 5));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(56, "Moderna", "2021-08-15", 1, 6));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(57, "Hepatitis B", "2019-01-19", 1, 7));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(58, "Pfizer", "2021-11-29", 1, 8));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(59, "Rabies", "2022-07-03", 1, 9));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(60, "Hepatitis A", "2022-04-16", 1, 10));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(61, "Polio", "2022-09-12", 1, 11));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(62, "Moderna", "2022-10-31", 1, 12));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(63, "Hepatitis B", "2021-09-04", 1, 13));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(64, "Pfizer", "2021-01-08", 1, 14));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(65, "Rabies", "2021-07-26", 1, 15));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(66, "Hepatitis A", "2022-03-27", 1, 16));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(67, "Polio", "2020-08-13", 1, 17));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(68, "Moderna", "2021-11-11", 1, 18));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(69, "Hepatitis B", "2023-01-30", 1, 19));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(70, "Pfizer", "2022-06-09", 1, 20));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(71, "Rabies", "2022-12-16", 1, 21));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(72, "Hepatitis A", "2023-02-26", 1, 22));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(73, "Polio", "2021-05-18", 1, 23));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(74, "Moderna", "2022-08-06", 1, 24));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(75, "Hepatitis B", "2022-02-03", 1, 25));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(76, "Pfizer", "2020-09-23", 1, 15));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(77, "Rabies", "2020-03-02", 1, 2));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(78, "Hepatitis A", "2023-01-17", 1, 3));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(79, "Polio", "2022-04-24", 1, 4));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(80, "Moderna", "2021-07-09", 1, 5));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(81, "Hepatitis B", "2024-10-16", 1, 6));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(82, "Pfizer", "2022-02-15", 1, 7));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(83, "Rabies", "2020-08-11", 1, 8));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(84, "Hepatitis A", "2022-03-27", 1, 9));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(85, "Polio", "2020-06-08", 1, 10));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(86, "Moderna", "2021-10-24", 1, 11));
            vaccinationService.saveVaccinationReport(new VaccinationRequest(87, "Hepatitis B", "2022-09-01", 1, 12));

            //save diagnosis data

            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(1, 1, "Stroke", "Cardiovascular",
                    "Patient's stroke recovery progressing", 24, 29));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(2, 2, "Stroke", "Cardiovascular",
                    "Patient had a stroke", 4, 30));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(3, 3, "Asthma", "Respiratory",
                    "Patient was diagnosed with asthma", 5, 33));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(4, 4, "Lung Cancer", "Neoplasms",
                    "Patient was diagnosed with lung cancer", 6, 29));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(5, 5, "Common Cold", "Infectious",
                    "Patient had another episode of common cold", 7, 31));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(6, 6, "Hypertension", "Cardiovascular",
                    "Patient's blood pressure was high", 8, 35));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(7, 7, "Asthma", "Respiratory",
                    "Patient's asthma worsened", 9, 27));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(8, 8, "Leukemia", "Neoplasms",
                    "Patient was diagnosed with leukemia", 10, 32));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(9, 9, "Influenza", "Infectious",
                    "Patient had influenza symptoms", 11, 34));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(10, 10, "Hypertension", "Cardiovascular",
                    "Patient's blood pressure remained high", 12, 28));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(11, 11, "Asthma", "Respiratory",
                    "Patient had an asthma attack", 13, 30));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(12, 12, "Lung Cancer", "Neoplasms",
                    "Patient's lung cancer progressed", 14, 33));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(13, 13, "Common Cold", "Infectious",
                    "Patient had recurrent common cold", 15, 29));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(14, 14, "Stroke", "Cardiovascular",
                    "Patient suffered from another stroke", 16, 31));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(15, 15, "Asthma", "Respiratory",
                    "Patient's asthma symptoms improved", 17, 35));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(16, 16, "Lung Cancer", "Neoplasms",
                    "Patient's lung cancer treatment started", 18, 27));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(17, 17, "Common Cold", "Infectious",
                    "Patient had a severe cold", 19, 32));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(18, 18, "Hypertension", "Cardiovascular",
                    "Patient's blood pressure stabilized", 20, 34));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(19, 19, "Asthma", "Respiratory",
                    "Patient's asthma symptoms worsened", 21, 28));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(20, 20, "Leukemia", "Neoplasms",
                    "Patient's leukemia treatment ongoing", 22 ,30));
            diagnosisService.saveDiagnosis(new DiagnosisRequestDto(21, 21, "Common Cold", "Infectious",
                    "Patient had persistent cold symptoms", 23, 33));


            //save prescription
            prescriptionService.savePrescription(new PrescriptionRequestDto(1, "Ibuprofen", "Analgesics", 150.00,
                    "ONCE", "Before food", 10, "2022-03-15", 3, 29, 1));
            prescriptionService.savePrescription(new PrescriptionRequestDto(2, "Ibuprofen", "Analgesics", 150.00,
                    "ONCE", "Before food", 10, "2022-03-15", 4, 30, 2));
            prescriptionService.savePrescription(new PrescriptionRequestDto(3, "Azithromycin", "Antibiotics", 200.00,
                    "TWICE", "After food", 7, "2022-05-07", 5, 33, 3));
            prescriptionService.savePrescription(new PrescriptionRequestDto(4, "Aspirin", "Analgesics", 80.00,
                    "THRICE", "Before food", 14, "2022-06-22", 6, 29, 4));
            prescriptionService.savePrescription(new PrescriptionRequestDto(5, "Meloxicam", "Anti-inflammatory", 120.00,
                    "TWICE", "After food", 7, "2022-08-01", 7, 31, 5));
            prescriptionService.savePrescription(new PrescriptionRequestDto(6, "Amoxicillin", "Antibiotics", 180.00,
                    "TWICE", "Before food", 10, "2022-09-16", 8, 35, 6));
            prescriptionService.savePrescription(new PrescriptionRequestDto(7, "Paracetamol", "Analgesics", 100.00,
                    "TWICE", "Before bed", 7, "2024-12-27", 9, 27, 7));
            prescriptionService.savePrescription(new PrescriptionRequestDto(8, "Diclofenac", "Anti-inflammatory", 110.00,
                    "THRICE", "After food", 14, "2022-11-04", 10, 32, 8));
            prescriptionService.savePrescription(new PrescriptionRequestDto(9, "Paracetamol", "Analgesics", 100.00,
                    "TWICE", "Before bed", 7, "2022-12-19", 11, 34, 9));
            prescriptionService.savePrescription(new PrescriptionRequestDto(10, "Ibuprofen", "Analgesics", 150.00,
                    "ONCE", "After food", 10, "2023-02-06", 12, 28, 10));
            prescriptionService.savePrescription(new PrescriptionRequestDto(11, "Azithromycin", "Antibiotics", 200.00,
                    "TWICE", "Before food", 7, "2023-03-20", 13, 30, 11));
            prescriptionService.savePrescription(new PrescriptionRequestDto(12, "Aspirin", "Analgesics", 80.00,
                    "THRICE", "After food", 14, "2023-05-05", 14, 33, 12));
            prescriptionService.savePrescription(new PrescriptionRequestDto(13, "Meloxicam", "Anti-inflammatory", 120.00,
                    "TWICE", "Before bed", 7, "2023-06-20", 15, 29, 13));
            prescriptionService.savePrescription(new PrescriptionRequestDto(14, "Amoxicillin", "Antibiotics", 180.00,
                    "TWICE", "After food", 10, "2023-08-06", 16, 31, 14));
            prescriptionService.savePrescription(new PrescriptionRequestDto(15, "Losartan", "Antihypertensives", 160.00,
                    "ONCE", "Before food", 10, "2023-09-21", 17, 35, 15));
            prescriptionService.savePrescription(new PrescriptionRequestDto(16, "Diclofenac", "Anti-inflammatory", 110.00,
                    "THRICE", "After food", 14, "2023-11-08", 18, 27, 16));
            prescriptionService.savePrescription(new PrescriptionRequestDto(17, "Paracetamol", "Analgesics", 100.00,
                    "TWICE", "Before bed", 7, "2023-12-23", 19, 32, 17));
            prescriptionService.savePrescription(new PrescriptionRequestDto(18, "Ibuprofen", "Analgesics", 150.00,
                    "ONCE", "After food", 10, "2024-02-09", 20, 34, 18));
            prescriptionService.savePrescription(new PrescriptionRequestDto(19, "Azithromycin", "Antibiotics", 200.00,
                    "TWICE", "Before food", 7, "2024-03-24", 21, 28, 19));
            prescriptionService.savePrescription(new PrescriptionRequestDto(20, "Aspirin", "Analgesics", 80.00,
                    "THRICE", "After food", 14, "2024-05-09", 22, 30, 20));
            prescriptionService.savePrescription(new PrescriptionRequestDto(21, "Meloxicam", "Anti-inflammatory", 120.00,
                    "TWICE", "Before bed", 7, "2024-06-24", 23, 33, 21));
            }
    }

    private void loadSqlScript(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String sqlScript = new String(bytes, StandardCharsets.UTF_8);
        jdbcTemplate.execute(sqlScript);
    }
}