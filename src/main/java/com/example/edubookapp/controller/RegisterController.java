package com.example.edubookapp.controller;

import com.example.edubookapp.model.User;
import com.example.edubookapp.service.UserService;
import com.example.edubookapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String getRegister(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes){
        userValidator.validate(user,result);
        if (result.hasErrors()){
            return "register";
        }
        userService.register(user);
        redirectAttributes.addFlashAttribute("registerSuccess","You have successfully registered!");
        return "redirect:/login?register";
    }
}
