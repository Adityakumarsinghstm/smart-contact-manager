package com.smart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.dao.UserRepository;
import com.smart.entities.Contact;
import com.smart.entities.User;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addCommonData(Model model, Principal principal)
    {
        String userName = principal.getName();
        //System.out.println("userName " + userName);
        User user = userRepository.getUserByUserName(userName);
        //System.out.println(user);
        model.addAttribute("user", user);
    }
    @RequestMapping("/index")
    public String dashboard()
    {
        
        return "normal/user_dashboard";
    }

    @GetMapping("/add-contact")
    public String opendAddContact(Model model)
    {
        model.addAttribute("title", "Add Contacts");
        model.addAttribute("contact", new Contact());
        return "normal/add_contact_form";
    }
}
