package com.example.edubookapp.controller;

import com.example.edubookapp.model.Book;
import com.example.edubookapp.model.Comment;
import com.example.edubookapp.security.CustomUserDetail;
import com.example.edubookapp.model.User;
import com.example.edubookapp.service.BookService;
import com.example.edubookapp.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private BookService bookService;
    @PostMapping("/book/{id}/comment")
    public String comment(@PathVariable("id") Integer id,
                          @RequestParam("content") String content){
        Book book = bookService.findOne(id).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((User)authentication.getPrincipal());
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreated(new Date());
        comment.setBook(book);
        comment.setUser(currentUser);
//        book.addComments(comment);
//        currentUser.addComments(comment);
//        System.out.println(book.getComments().size());
        commentService.save(comment);
//        model.addAttribute("comment",comment);
        return "redirect:/book/{id}";
    }
    @GetMapping("/book/{id}/comment/{comment_id}/delete")
    public String delete(@PathVariable("id") Integer id,
                         @PathVariable("comment_id") Integer commentId,
                         RedirectAttributes redirectAttributes){
        Book book = bookService.findOne(id).get();
        Comment comment = commentService.findOne(commentId).get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((User)authentication.getPrincipal());
        if ((comment.getUser().getUsername().equalsIgnoreCase(currentUser.getUsername()))
                || (currentUser.getUsername().equalsIgnoreCase("admin"))){
            commentService.delete(commentId);
            redirectAttributes.addFlashAttribute("success","Delete comment successfully");
            return "redirect:/book/{id}";
        }
        else {
            redirectAttributes.addFlashAttribute("fail","Cannot delete this comment");
            return "redirect:/book/{id}";
        }
    }
}
