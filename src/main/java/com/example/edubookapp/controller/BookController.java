package com.example.edubookapp.controller;

import com.example.edubookapp.model.PendingBook;
import com.example.edubookapp.model.User;
import com.example.edubookapp.security.CurrentUser;
import com.example.edubookapp.security.CustomUserDetail;
import com.example.edubookapp.service.*;
import com.example.edubookapp.validator.PendingBookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private PendingBookService pendingBookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PendingBookValidator pendingBookValidator;
    @Autowired
    private LikeService likeService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/book/add")
    public String add(Model model) {
        model.addAttribute("pendingBook", new PendingBook());
        model.addAttribute("categories", categoryService.findAll());
        return "pending_book_form";
    }

    @GetMapping("/book/{id}")
    public String show(@PathVariable("id") Integer id, Model model, @CurrentUser CustomUserDetail currentUser) {
        if (currentUser != null) {
            User user = userService.findByUserName(currentUser.getUsername());
            if (likeService.findByBookIdAndUserId(id, user.getId()).isPresent()) {
                model.addAttribute("liked", likeService.findByBookIdAndUserId(id, user.getId()).get());
            } else {
                model.addAttribute("liked", "None");
            }
            model.addAttribute("username", currentUser.getUsername());
        } else model.addAttribute("liked", "None");
        model.addAttribute("book", bookService.findOne(id).get());
        model.addAttribute("categories", categoryService.findAll());
        return "book_detail";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/pending/book/{requestUsername}/{id}")
    public String showPending(@CurrentUser CustomUserDetail currentUser,
                              @PathVariable("requestUsername") String requestUsername,
                              @PathVariable("id") Integer id, Model model) {
        try {
            if (currentUser.getAuthorities().size() != 2) {
                if (!currentUser.getUsername().equalsIgnoreCase(requestUsername)) {
                    return "403";
                }
            }
        } catch (NullPointerException e) {
            return "redirect:/login";
        }
        model.addAttribute("book", pendingBookService.findByIdAndRequestUsername(id, requestUsername));
        model.addAttribute("categories", categoryService.findAll());
        return "pending_book_detail";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/pending/book/{requestUsername}/{id}/edit")
    public String editPendingBook(@CurrentUser CustomUserDetail currentUser,
                                  @PathVariable("requestUsername") String requestUsername,
                                  @PathVariable("id") Integer id,
                                  Model model) {
        try {
            if (currentUser.getAuthorities().size() != 2) {
                if (!currentUser.getUsername().equalsIgnoreCase(requestUsername)) {
                    return "403";
                }
            }
        } catch (NullPointerException e) {
            return "redirect:/login";
        }
        model.addAttribute("pendingBook", pendingBookService.findOneWithCategory(id));
        model.addAttribute("categories", categoryService.findAll());
        return "pending_book_form";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/book/save")
    public String savePending(@Valid PendingBook pendingBook,
                              BindingResult result,
                              @CurrentUser CustomUserDetail currentUser,
                              Model model, RedirectAttributes redirect) {
        pendingBookValidator.validate(pendingBook, result);
        model.addAttribute("categories", categoryService.findAll());
        if (result.hasErrors()) {
            return "pending_book_form";
        }
        pendingBook.setRequestUsername(currentUser.getUsername());
        pendingBookService.save(pendingBook);
        redirect.addFlashAttribute("success", "Saved this book successfully");
        return "redirect:/userInfo";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/pending/book/{requestUsername}/{id}/upload")
    public String getUploadPending(@CurrentUser CustomUserDetail currentUser,
                                   @PathVariable("requestUsername") String requestUsername,
                                   @PathVariable("id") Integer id, Model model) {
        try {
            if (currentUser.getAuthorities().size() != 2) {
                if (!currentUser.getUsername().equalsIgnoreCase(requestUsername)) {
                    return "403";
                }
            }
        } catch (NullPointerException e) {
            return "redirect:/login";
        }
        model.addAttribute("book", pendingBookService.findByIdAndRequestUsername(id, requestUsername));
        model.addAttribute("categories", categoryService.findAll());
        return "pending_book_upload";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/pending/book/{requestUsername}/{id}/upload/image")
    public String postUploadImage(@PathVariable("requestUsername") String requestUsername,
                                  @PathVariable("id") Integer id,
                                  @RequestParam("imageLink") MultipartFile imageFile,
                                  RedirectAttributes redirectAttributes) {
        PendingBook book = pendingBookService.findByIdAndRequestUsername(id, requestUsername);
        pendingBookService.uploadImage(book, imageFile);

        redirectAttributes.addFlashAttribute("success", "Upload image successfully");
        return "redirect:/pending/book/{requestUsername}/{id}";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/pending/book/{requestUsername}/{id}/upload/content")
    public String postUploadContent(@PathVariable("requestUsername") String requestUsername,
                                    @PathVariable("id") Integer id,
                                    @RequestParam("contentLink") MultipartFile contentFile,
                                    RedirectAttributes redirectAttributes) {
        PendingBook book = pendingBookService.findByIdAndRequestUsername(id, requestUsername);
        pendingBookService.uploadContent(book, contentFile);

        redirectAttributes.addFlashAttribute("success", "Upload content successfully");
        return "redirect:/pending/book/{requestUsername}/{id}";
    }

    @GetMapping("/read/{contentLink}")
    public String readPdf(@PathVariable("contentLink") String contentLink,
                          Model model) {
        model.addAttribute("contentLink", contentLink + ".pdf");
        return "read_pdf";
    }
}
