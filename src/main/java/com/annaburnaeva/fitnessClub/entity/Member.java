package com.annaburnaeva.fitnessClub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Table
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    private String phoneNumber;

    @ManyToMany(mappedBy = "members")
    private List<FitnessClass> fitnessClasses;

    @Override
    public String toString() {
        return this.email;
    }


}