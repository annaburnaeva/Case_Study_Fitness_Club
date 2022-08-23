package com.annaburnaeva.fitnessClub.service;

import com.annaburnaeva.fitnessClub.entity.FitnessClass;
import com.annaburnaeva.fitnessClub.entity.Member;
import com.annaburnaeva.fitnessClub.exception.FitnessClassNotFoundException;
import com.annaburnaeva.fitnessClub.repository.FitnessClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class FitnessClassService {
    @Autowired
    private FitnessClassRepository fitnessClassRepository;


    @Transactional(readOnly = true)
    public List<FitnessClass> getAllFitnessClasses() {

        return fitnessClassRepository.findAll();
    }
    @Transactional
    public void saveFitnessClass(FitnessClass fitnessClass) {

        fitnessClassRepository.save(fitnessClass);
    }
    @Transactional(readOnly = true)
    public FitnessClass getFitnessClassById(Long id) throws FitnessClassNotFoundException {
        Optional<FitnessClass> resultFitnessClass = fitnessClassRepository.findById(id);
        if (resultFitnessClass.isPresent()){
            return resultFitnessClass.get();
        }
        throw new FitnessClassNotFoundException("Fitness class with id " + id + "not found");
    }

    @Transactional
    public void deleteFitnessClassByID(Long id) throws FitnessClassNotFoundException {
       Long count = fitnessClassRepository.countById(id);
        if (count == null || count == 0) {
            throw new FitnessClassNotFoundException("Fitness class with id " + id + "not found");

        }
        fitnessClassRepository.deleteById(id);
    }

    public List<Member> getMembersByFitnessClassId(Long id) {
        Optional<FitnessClass> resultFitnessClass = fitnessClassRepository.findById(id);
        List<Member> membersByFitnessClass = resultFitnessClass.get().getMembers();
        return membersByFitnessClass;
    }



//    @Transactional
//    public void updateFitnessClass(Long fitnessClassId, FitnessClass fitnessClassRequest) {
//        FitnessClass fitnessClass = findFitnessClassById(fitnessClassId);
//        fitnessClass.setName(fitnessClassRequest.getName());
//        fitnessClass.setPrice(fitnessClassRequest.getPrice());
//        fitnessClass.setInstructor(fitnessClassRequest.getInstructor());
//        fitnessClassRepository.save(fitnessClass);
//    }
//
//    public FitnessClass findFitnessClassById(Long fitnessClassId) {
//        return fitnessClassRepository.findById(fitnessClassId)
//                .orElseThrow(() -> new RuntimeException("Fitness Class with id " + fitnessClassId + "not found"));
//    }
//
//    @Transactional
//    public double getFitnessClassPrice(FitnessClass fitnessClass) {
//        FitnessClass currentFitnessClass = findFitnessClassById(fitnessClass.getId());
//        return currentFitnessClass.getPrice();
//
//    }
}
