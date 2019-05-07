package com.example.edubookapp.controller;

import com.example.edubookapp.model.Book;
import com.example.edubookapp.model.Like;
import com.example.edubookapp.model.User;
import com.example.edubookapp.security.CurrentUser;
import com.example.edubookapp.security.CustomUserDetail;
import com.example.edubookapp.service.BookService;
import com.example.edubookapp.service.LikeService;
import com.example.edubookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LikeController {
    @Autowired
    private BookService bookService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/book/{id}/like")
    public String likeBook(@PathVariable("id") Integer id,
                           @CurrentUser CustomUserDetail customUserDetail) {
        Book book = bookService.findOne(id).get();
        User user = userService.findByUserName(customUserDetail.getUsername());
        Like like = new Like();
        like.setBook(book);
        like.setUser(user);
        likeService.save(like);
        return "redirect:/book/{id}";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/book/{id}/delete/like")
    public String deleteLikeBook(@PathVariable("id") Integer id,
                                 @CurrentUser CustomUserDetail customUserDetail) {
        User user = userService.findByUserName(customUserDetail.getUsername());
        Like like = likeService.findByBookIdAndUserId(id, user.getId()).get();
        likeService.delete(like.getId());
        return "redirect:/book/{id}";
    }
}
