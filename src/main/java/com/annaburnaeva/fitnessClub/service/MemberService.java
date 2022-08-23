package com.annaburnaeva.fitnessClub.service;

import com.annaburnaeva.fitnessClub.entity.FitnessClass;
import com.annaburnaeva.fitnessClub.entity.Member;
import com.annaburnaeva.fitnessClub.exception.MemberNotFoundException;
import com.annaburnaeva.fitnessClub.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {

        return memberRepository.findAll();
    }
    public List<FitnessClass> getAllFitnessClassesByMemberId(Long id){
        Member member = new Member();
        return member.getFitnessClasses();
    }

    public Member saveMember(Member member) {

        return memberRepository.save(member);
    }

    public void deleteMember(Long id) throws MemberNotFoundException {
        Long count = memberRepository.countById(id);
        if (count == null || count == 0) {
            throw new MemberNotFoundException("Member with id " + id + "not found");

        }
        memberRepository.deleteById(id);
    }

    public Member getMemberById(Long id) throws MemberNotFoundException {
        Optional<Member> resultMember = memberRepository.findById(id);
        if (resultMember.isPresent()) {
            return resultMember.get();
        }
        throw new MemberNotFoundException("Member with id " + id + "not found");
    }

//
//    private Member findMemberById(Long memberId) {
//        return memberRepository.findById(memberId)
//                .orElseThrow(() -> new RuntimeException("Member with id " + memberId + "not found"));
//    }
}
