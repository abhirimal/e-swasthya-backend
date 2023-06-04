package com.star.eswasthyabackend.model.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District districtId;

    private String streetAddress;

    private Integer wardNo;
}
