package com.star.eswasthyabackend.model.location;

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

public class District {

    @Id
    private Integer id;

    private String name;

    private String provinceNo;

    private String provinceName;

}
