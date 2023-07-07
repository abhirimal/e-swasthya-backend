package com.star.eswasthyabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AIDiagnosedDiseaseRequestDto {

    private String name;

    @NotBlank(message = "Image cannot be blank.")
    private String imagePath;

    private Boolean isDetected;

    @NotNull(message = "Appointment Id cannot be null.")
    private Integer appointmentId;
}
