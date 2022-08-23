package com.annaburnaeva.fitnessClub.controller;

import com.annaburnaeva.fitnessClub.entity.FitnessClass;
import com.annaburnaeva.fitnessClub.entity.Member;
import com.annaburnaeva.fitnessClub.exception.FitnessClassNotFoundException;
import com.annaburnaeva.fitnessClub.service.FitnessClassService;
import com.annaburnaeva.fitnessClub.service.MemberService;
import com.annaburnaeva.fitnessClub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FitnessClassController {
    @Autowired
    private FitnessClassService fitnessClassService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/fitness_classes")
    public String getAllFitnessClasses(Model model) {
        List<FitnessClass> fitnessClasses = fitnessClassService.getAllFitnessClasses();
        model.addAttribute("fitnessClasses", fitnessClasses);
        return "fitness_classes";
    }

    @GetMapping("/fitness_classes/delete/{id}")
    public String deleteFitnessClassById(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        try {
            fitnessClassService.deleteFitnessClassByID(id);
            redirectAttributes.addFlashAttribute("message", "The fitness class with id " + id + " has been deleted");
        } catch (FitnessClassNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/fitness_classes";
    }


    @GetMapping("fitness_classes/edit/{id}")
    public String editFitnessClassById(@PathVariable(name = "id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            FitnessClass fitnessClass = fitnessClassService.getFitnessClassById(id);
            model.addAttribute("fitnessClass", fitnessClass);
            model.addAttribute("pageTitle", "Edit fitness class (ID: " + id + ")");
            return "fitness_class_form";
        } catch (FitnessClassNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/fitness_classes";
        }
    }

    @GetMapping("/fitness_class_form")
    public String showFitnessClassForm(Model model) {
        model.addAttribute("fitnessClass", new FitnessClass());
        model.addAttribute("pageTitle", "Add new fitness class");
        return "fitness_class_form";
    }

    @RequestMapping(value = "/fitness_classes/save", method = RequestMethod.POST)
    public String saveFitnessClass(@ModelAttribute("fitnessClass") FitnessClass fitnessClass, RedirectAttributes redirectAttributes) {
        fitnessClassService.saveFitnessClass(fitnessClass);
        redirectAttributes.addFlashAttribute("message", "The fitness class has been saved successfully");
        return "redirect:/fitness_classes";
    }

    @GetMapping("fitness_classes/add_to_member/{id}")
    public String addFitnessClassToMember(@PathVariable(name = "id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            FitnessClass fitnessClass = fitnessClassService.getFitnessClassById(id);
            model.addAttribute("fitnessClass", fitnessClass);
            model.addAttribute("pageTitle", "Adding fitness class (ID: " + id + ")");
            List<Member> members = memberService.getAllMembers();
            model.addAttribute("members", members);
            return "fitness_class_members";
        } catch (FitnessClassNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/fitness_classes";
        }
    }
}

