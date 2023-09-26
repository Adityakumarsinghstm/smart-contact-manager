package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepository;
import com.smart.entities.User;
import com.smart.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - smart contact manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("title", "Register - smart contact manager");
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
            @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
            HttpSession session) {

        try {
            if (!agreement) {
                System.out.println("You have not checked terms and condition ");
                throw new Exception("You have not checked terms and condition ");
            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("default.png");

            System.out.println("agreement   " + agreement);
            System.out.println("User    " + user);

            User result = userRepository.save(user);
            model.addAttribute("user", result);

            session.setAttribute("message", new Message("User Added successfully !!", "alert-success"));
            return "signup";

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong !!" + e.getMessage(), "alert-danger"));
            return "signup";
        }

    }
}
