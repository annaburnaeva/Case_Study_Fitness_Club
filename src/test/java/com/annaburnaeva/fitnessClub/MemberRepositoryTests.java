package com.annaburnaeva.fitnessClub;

import com.annaburnaeva.fitnessClub.entity.FitnessClass;
import com.annaburnaeva.fitnessClub.entity.Member;
import com.annaburnaeva.fitnessClub.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testAddNew() {
        Member member = new Member();
        FitnessClass fitnessClass = new FitnessClass(1L, "zumba", 200.0);
        List<FitnessClass> fitnessClasses = new ArrayList<>();
        fitnessClasses.add(fitnessClass);
        member.setFirstName("Anna");
        member.setLastName("Burnaeva");
        member.setPhoneNumber("4124034540");
        member.setEmail("anna@gmail.com");
        member.setFitnessClasses(fitnessClasses);
        Member savedMember = memberRepository.save(member);
        Assertions.assertThat(savedMember).isNotNull();
        Assertions.assertThat(savedMember.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Member> members = memberRepository.findAll();
        Assertions.assertThat(members).hasSizeGreaterThan(0);

        for (Member member : members) {
            System.out.println(member);
        }
    }

    @Test
    public void testUpdate() {
        long memberId = 1L;
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.get();
        member.setPhoneNumber("4124034641");
        memberRepository.save(member);
        Member updatedMember = memberRepository.findById(memberId).get();
        Assertions.assertThat(updatedMember.getPhoneNumber()).isEqualTo("4124034641");
    }

    @Test
    public void testGet() {
        long memberId = 1L;
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Assertions.assertThat(optionalMember).isPresent();
        System.out.println(optionalMember.get());
    }

    @Test
    public void testDelete() {
        long memberId = 1L;
        memberRepository.deleteById(memberId);
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Assertions.assertThat(optionalMember).isNotPresent();
    }

}

