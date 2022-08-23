package com.annaburnaeva.fitnessClub.controller;

import com.annaburnaeva.fitnessClub.entity.*;
import com.annaburnaeva.fitnessClub.exception.FitnessClassNotFoundException;
import com.annaburnaeva.fitnessClub.exception.MemberNotFoundException;
import com.annaburnaeva.fitnessClub.repository.FitnessClassRepository;
import com.annaburnaeva.fitnessClub.service.FitnessClassService;
import com.annaburnaeva.fitnessClub.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MemberAccountController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private FitnessClassService fitnessClassService;

    @GetMapping("/member_account/{id}")
    public String getMember(@PathVariable(name = "id") Long id, Model model) throws MemberNotFoundException {
        Member member = memberService.getMemberById(id);
        ;
        List<FitnessClass> fitnessClasses = member.getFitnessClasses();
        model.addAttribute("fitnessClasses", fitnessClasses);
        model.addAttribute("member", member);
        model.addAttribute("pageTitle", "Member Account");
        return "member_account";
    }

    @PostMapping("/member_account/add/{memberid}/{fitness_class_id}")
    public String addFitnessClassToAccount(@PathVariable(name = "memberid") Long memberId,
                                           @PathVariable(name = "fitness_class_id") Long fitnessClassId,
                                           Model model,
                                           RedirectAttributes redirectAttributes) throws FitnessClassNotFoundException, MemberNotFoundException {
        try {
            FitnessClass fitnessClass = fitnessClassService.getFitnessClassById(fitnessClassId);
            Member member = memberService.getMemberById(memberId);
            List<FitnessClass> fitnessClasses = member.getFitnessClasses();
            model.addAttribute("member", member);
            model.addAttribute("pageTitle", "Edit member (ID: " + memberId + ")");
            fitnessClasses.add(fitnessClass);
            return "redirect:/member_account";
        } catch (FitnessClassNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "Already have");
            return "redirect:/member_account";
        }
    }

    @PostMapping("/member_account/delete/{memberid}/{fitness_class_id}")
    public String deleteFitnessClassFromAccount(@PathVariable(name = "memberid") Long memberId,
                                                @PathVariable(name = "fitness_class_id") Long fitnessClassId,
                                                RedirectAttributes redirectAttributes) throws MemberNotFoundException, FitnessClassNotFoundException {
        Member member = memberService.getMemberById(memberId);
        FitnessClass fitnessClass = fitnessClassService.getFitnessClassById(fitnessClassId);
        List<FitnessClass> membersFitnessClasses = member.getFitnessClasses();
        membersFitnessClasses.remove(fitnessClass);
        return "redirect:/member_account";
    }

}
