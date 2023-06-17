package com.star.eswasthyabackend.utility;

import com.star.eswasthyabackend.repository.HospitalRepository;
import com.star.eswasthyabackend.repository.location.DistrictRepository;
import com.star.eswasthyabackend.repository.location.MunicipalityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class OneTimetDataLoader implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    private final DistrictRepository districtRepository;
    private final MunicipalityRepository municipalityRepository;
    private final HospitalRepository hospitalRepository;

    public OneTimetDataLoader(JdbcTemplate jdbcTemplate, DistrictRepository districtRepository, MunicipalityRepository municipalityRepository, HospitalRepository hospitalRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.districtRepository = districtRepository;
        this.municipalityRepository = municipalityRepository;
        this.hospitalRepository = hospitalRepository;
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
    }

    private void loadSqlScript(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        String sqlScript = new String(bytes, StandardCharsets.UTF_8);
        jdbcTemplate.execute(sqlScript);
    }
}