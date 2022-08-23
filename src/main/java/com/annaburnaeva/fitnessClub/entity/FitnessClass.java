package com.annaburnaeva.fitnessClub.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FitnessClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;
    @ManyToMany
    @JoinTable(name = "fitness_class_member",
            joinColumns = @JoinColumn(name = "fitness_class_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))

    private List<Member> members;

    public FitnessClass(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public List<Member> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
