package com.star.eswasthyabackend.dto.user.patient;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class PatientDetailsRequestDto {

    private Integer patientDetailId;

    private Integer userId;

    @NotBlank(message = "Citizenship Number is required")
    private String citizenshipNo;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits without any special character.")
    private String phoneNumber;

    private String weight;

    private String height;

    private String gender;

    private String bloodGroup;

    //used while listening or sending data to front end
    private String imagePath;

    @NotNull(message = "Municipality id cannot be null")
    private Integer municipalityId;

    @NotBlank(message = "Street name cannot be empty")
    private String streetAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    public PatientDetailsRequestDto(Integer userId, String citizenshipNo, String phoneNumber, String weight, String height,
                                    String gender, String bloodGroup, String imagePath, Integer municipalityId,
                                    String streetAddress, String dateOfBirth){
        this.userId = userId;
        this.citizenshipNo = citizenshipNo;
        this.phoneNumber = phoneNumber;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        this.imagePath = imagePath;
        this.municipalityId = municipalityId;
        this.streetAddress = streetAddress;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

}
