package com.example.edubookapp.controller;

import com.example.edubookapp.service.BookService;
import com.example.edubookapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("categories",categoryService.findAll());
        return "index";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/search")
    public String search(@RequestParam("s") String s, Model model){
        if (s.isEmpty()){
            return "redirect:/";
        }
        System.out.println("asdasdasdasd");
        model.addAttribute("books",bookService.search(s));
        model.addAttribute("categories",categoryService.findAll());
        return "book_search";
    }
}
