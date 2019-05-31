package com.example.edubookapp.controller;

import com.example.edubookapp.model.User;
import com.example.edubookapp.security.CurrentUser;
import com.example.edubookapp.security.CustomUserDetail;
import com.example.edubookapp.service.CategoryService;
import com.example.edubookapp.service.PendingBookService;
import com.example.edubookapp.service.UserService;
import com.example.edubookapp.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/userInfo/upload/avatar")
    public String postAvatar(@CurrentUser CustomUserDetail customUserDetail,
                             @RequestParam("avatar") MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes) {
        User user = userService.findByUserName(customUserDetail.getUsername());
        userService.uploadAvatar(user, multipartFile);
        redirectAttributes.addFlashAttribute("success", "Upload avatar successfully");
        return "redirect:/userInfo";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/userInfo/delete/avatar")
    public String deleteAvatar(@CurrentUser CustomUserDetail customUserDetail,
                               RedirectAttributes redirectAttributes) {
        User user = userService.findByUserName(customUserDetail.getUsername());
        Path deletePath = Paths.get(Const.UPLOAD_AVATAR + "/" + user.getImageLink());
        user.deleteImage();
        try {
            Files.delete(deletePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userService.save(user);
        redirectAttributes.addFlashAttribute("success", "delete avatar successfully");
        return "redirect:/userInfo";
    }
}
