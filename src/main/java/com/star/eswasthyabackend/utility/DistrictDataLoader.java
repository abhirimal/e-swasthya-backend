//package com.star.eswasthyabackend.utility;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.util.FileCopyUtils;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
//@Component
//public class DistrictDataLoader implements CommandLineRunner {
//    private final JdbcTemplate jdbcTemplate;
//
//    public DistrictDataLoader(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        loadSqlScript("districts.sql");
//        loadSqlScript("municipality");
//    }
//
//    private void loadSqlScript(String fileName) throws IOException {
//        ClassPathResource resource = new ClassPathResource(fileName);
//        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
//        String sqlScript =  new String(bytes, StandardCharsets.UTF_8);
//        jdbcTemplate.execute(sqlScript);
//    }
//}
