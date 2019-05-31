package com.example.edubookapp.controller.admin;

import com.example.edubookapp.service.BookService;
import com.example.edubookapp.service.CategoryService;
import com.example.edubookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminDashboardController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/admin","/admin/dashboard"})
    public String index(Model model) {
        long totalUsers = userService.countAll();
        long totalBooks = bookService.countAll();
        model.addAttribute("totalUsers",totalUsers);
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("totalBooks",totalBooks);
        return "admin/dashboard";
    }
}
