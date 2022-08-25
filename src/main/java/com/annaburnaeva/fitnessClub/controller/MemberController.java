package com.annaburnaeva.fitnessClub.controller;

import com.annaburnaeva.fitnessClub.entity.FitnessClass;
import com.annaburnaeva.fitnessClub.entity.Member;
import com.annaburnaeva.fitnessClub.exception.FitnessClassNotFoundException;
import com.annaburnaeva.fitnessClub.exception.MemberNotFoundException;
import com.annaburnaeva.fitnessClub.service.FitnessClassService;
import com.annaburnaeva.fitnessClub.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private FitnessClassService fitnessClassService;

    @GetMapping("/members")
    public String getAllMembers(Model model) {
        List<Member> members = memberService.getAllMembers();
        model.addAttribute("members", members);
        return "members";
    }

    @GetMapping("/member_form")
    public String showMemberForm(Model model) {
        model.addAttribute("member", new Member());
        model.addAttribute("pageTitle", "Add new member");
        return "member_form";
    }

    @GetMapping("members/edit/{id}")
    public String editMemberById(@PathVariable(name = "id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Member member = memberService.getMemberById(id);
            model.addAttribute("member", member);
            model.addAttribute("pageTitle", "Edit member (ID: " + id + ")");
            return "member_form";
        } catch (MemberNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/members";
        }

    }

    @RequestMapping(value = "/members/save", method = RequestMethod.POST)
    public String saveMember(@ModelAttribute("member") Member member, RedirectAttributes redirectAttributes) {
        memberService.saveMember(member);
        redirectAttributes.addFlashAttribute("message", "The member has been saved successfully");
        return "redirect:/members";
    }

    @GetMapping("/members/delete/{id}")
    public String deleteMemberById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        try {
            memberService.deleteMember(id);
            redirectAttributes.addFlashAttribute("message", "The member with id " + id + " has been deleted");
        } catch (MemberNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/members";
    }

    @GetMapping("fitness_class_members/add_to_fitness_class/{fitnessClassId}/{memberId}")
    public String addMemberToFitnessClass(@PathVariable(name = "fitnessClassId") Long fitnessClassId,
                                          @PathVariable(name = "memberId") Long memberId,
                                          Model model, RedirectAttributes redirectAttributes)
            throws FitnessClassNotFoundException, MemberNotFoundException {
        try {
            Member member = memberService.getMemberById(memberId);
            FitnessClass fitnessClass = fitnessClassService.getFitnessClassById(fitnessClassId);
            List<FitnessClass> currentFitnessClasses = member.getFitnessClasses();
            currentFitnessClasses.add(fitnessClass);
            List<Member> currentMembers = fitnessClass.getMembers();
            currentMembers.add(member);
//            model.addAttribute("member", new Member());
            model.addAttribute("member", member);
            model.addAttribute("fitnessClass", fitnessClass);
            memberService.saveMember(member);
            fitnessClassService.saveFitnessClass(fitnessClass);
            redirectAttributes.addFlashAttribute("message", "The member with id " + memberId + " was successfully sign up ");
            return "redirect:/fitness_classes";
        } catch (MemberNotFoundException | FitnessClassNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "Member or Class not found");
            return "redirect:/members";
        }

    }
}
