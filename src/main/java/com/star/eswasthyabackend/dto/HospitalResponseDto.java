package com.star.eswasthyabackend.dto;

import com.star.eswasthyabackend.model.Hospital;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HospitalResponseDto {

    private Integer id;

    private String name;

    private Integer locationId;

    public HospitalResponseDto(Integer id, String name, Integer hospitalId){
        this.id = id;
        this.name = name;
        this.locationId = hospitalId;
    }
}
