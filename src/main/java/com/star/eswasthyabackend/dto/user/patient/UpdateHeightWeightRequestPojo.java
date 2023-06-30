package com.star.eswasthyabackend.dto.user.patient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateHeightWeightRequestPojo {

    private Integer patientId;

    private String height;

    private String weight;
}
