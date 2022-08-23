package com.annaburnaeva.fitnessClub;

import com.annaburnaeva.fitnessClub.entity.FitnessClass;
import com.annaburnaeva.fitnessClub.repository.FitnessClassRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class FitnessClassRepositoryTests {

    @Autowired
    private FitnessClassRepository fitnessClassRepository;

    @Test
    public void testAddNew() {
        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setName("fitness");
        fitnessClass.setPrice(150.0);
        FitnessClass savedFitnessClass = fitnessClassRepository.save(fitnessClass);
        Assertions.assertThat(savedFitnessClass).isNotNull();
        Assertions.assertThat(savedFitnessClass.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<FitnessClass> fitnessClasses = fitnessClassRepository.findAll();
        Assertions.assertThat(fitnessClasses).hasSizeGreaterThan(0);

        for (FitnessClass fitnessClass : fitnessClasses) {
            System.out.println(fitnessClass);
        }
    }

    @Test
    public void testUpdate() {
        long fitnessClassId = 1L;
        Optional<FitnessClass> optionalFitnessClass = fitnessClassRepository.findById(fitnessClassId);
        FitnessClass fitnessClass = optionalFitnessClass.get();
        fitnessClass.setPrice(180.0);
        fitnessClassRepository.save(fitnessClass);
        FitnessClass updatedMember = fitnessClassRepository.findById(fitnessClassId).get();
        Assertions.assertThat(updatedMember.getPrice()).isEqualTo(180.0);
    }

    @Test
    public void testGet() {
        long fitnessClassId = 1L;
        Optional<FitnessClass> optionalFitnessClass = fitnessClassRepository.findById(fitnessClassId);
        Assertions.assertThat(optionalFitnessClass).isPresent();
        System.out.println(optionalFitnessClass.get());
    }

    @Test
    public void testDelete() {
        long fitnessClassId = 1L;
        fitnessClassRepository.deleteById(fitnessClassId);
        Optional<FitnessClass> optionalFitnessClass = fitnessClassRepository.findById(fitnessClassId);
        Assertions.assertThat(optionalFitnessClass).isNotPresent();
    }

}

