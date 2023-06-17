package com.star.eswasthyabackend.model;

import com.star.eswasthyabackend.model.location.Location;
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
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String hospitalName;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
