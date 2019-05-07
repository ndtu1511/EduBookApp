package com.example.edubookapp.controller;

import com.example.edubookapp.security.CurrentUser;
import com.example.edubookapp.security.CustomUserDetail;
import com.example.edubookapp.service.CategoryService;
import com.example.edubookapp.service.PendingBookService;
import com.example.edubookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PendingBookService pendingBookService;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/userInfo")
    public String show(@CurrentUser CustomUserDetail customUserDetail, Model model) {
        model.addAttribute("user", userService.findByUserName(customUserDetail.getUsername()));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("pendingbooks", pendingBookService.findByRequestUsername(customUserDetail.getUsername()));
        return "user_info";
    }

}
