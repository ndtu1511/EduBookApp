package com.example.edubookapp.controller.admin;

import com.example.edubookapp.service.CategoryService;
import com.example.edubookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminUserController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @GetMapping("/admin/user")
    public String index(Model model){
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("users",userService.findAll());
        return "admin/user_list";
    }
}
