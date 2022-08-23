package com.annaburnaeva.fitnessClub.repository;

import com.annaburnaeva.fitnessClub.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public long countById(Long id);

    @Query("select m from Member m LEFT JOIN FETCH m.fitnessClasses f")
    List<Member> findAll();

}