package com.star.eswasthyabackend.model.diagnosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Disease {

    @Id
    private Integer id;

    private String diseaseName;

    private String diseaseType;
}
