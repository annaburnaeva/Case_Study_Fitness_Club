package com.annaburnaeva.fitnessClub.repository;

import com.annaburnaeva.fitnessClub.entity.FitnessClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface FitnessClassRepository extends JpaRepository<FitnessClass, Long> {

    public long countById(Long id);
//    List<FitnessClass> findAll();

//    @Query("select f from FitnessClass f LEFT JOIN FETCH f.members m")
    List<FitnessClass> findAll();
}

