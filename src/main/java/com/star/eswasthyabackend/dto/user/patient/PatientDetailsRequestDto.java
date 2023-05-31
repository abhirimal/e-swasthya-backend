package com.star.eswasthyabackend.dto.user.patient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class PatientDetailsRequestDto {

    private Integer id;

    private Integer userId;

    @NotBlank(message = "Citizenship Number is required")
    private String citizenshipNo;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits without any special character.")
    private String phoneNumber;

    private String weight;

    private String bloodGroup;

    // used while saving
    private MultipartFile imageFile;

    //used while listening or sending data to front end
    private String imagePath;

    @NotBlank(message = "Province cannot be empty")
    private String province;

    @NotBlank(message = "District cannot be empty")
    private String district;

    @NotBlank(message = "Ward cannot be empty")
    private String ward;

    private String street;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    }
