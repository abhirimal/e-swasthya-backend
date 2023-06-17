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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Location_SEQ_GEN")
    @SequenceGenerator(name = "Location_SEQ_GEN", sequenceName = "Location_SEQ", initialValue = 100, allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    private String streetAddress;

    private Integer wardNo;
}
