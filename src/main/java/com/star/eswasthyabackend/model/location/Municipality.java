package com.star.eswasthyabackend.model.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Municipality {

    @Id
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

}
