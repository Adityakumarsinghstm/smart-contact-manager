package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.smart.dao.UserRepository;



@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("title", "Home - smart contact manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about()
    {
        return "about";
    }

}
