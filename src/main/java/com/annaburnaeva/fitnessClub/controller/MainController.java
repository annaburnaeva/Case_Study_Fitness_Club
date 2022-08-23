package com.annaburnaeva.fitnessClub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/index")
    public String homepage() {

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @GetMapping("/pilates")
    public String pilates() {

        return "pilates";
    }

    @GetMapping("/dance")
    public String dance() {

        return "dance";
    }

    @GetMapping("/fitball")
    public String fitball() {

        return "fitball";
    }

    @GetMapping("/fitness_class_members")
    public String fitnessClassMembers() {

        return "fitness_class_members";
    }

}