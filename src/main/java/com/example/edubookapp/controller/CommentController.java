package com.example.edubookapp.controller;

import com.example.edubookapp.model.Book;
import com.example.edubookapp.model.Comment;
import com.example.edubookapp.model.PendingComment;
import com.example.edubookapp.model.User;
import com.example.edubookapp.security.CurrentUser;
import com.example.edubookapp.security.CustomUserDetail;
import com.example.edubookapp.service.BookService;
import com.example.edubookapp.service.CommentService;
import com.example.edubookapp.service.PendingCommentService;
import com.example.edubookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private PendingCommentService pendingCommentService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping(value = "/book/{id}/comment")
    public String comment(@PathVariable("id") Integer id,
                          @RequestParam("content") String content,
                          @CurrentUser CustomUserDetail customUserDetail,
                          RedirectAttributes redirectAttributes) {
        Book book = bookService.findOne(id).get();
        User currentUser = userService.findByUserName(customUserDetail.getUsername());
        PendingComment comment = new PendingComment();
        comment.setContent(content);
        comment.setCreated(new Date());
        comment.setBookname(book.getTitle());
        comment.setUsername(currentUser.getUsername());
        pendingCommentService.save(comment);
        redirectAttributes.addFlashAttribute("success", "Your Comment is Pending");
        return "redirect:/book/{id}";
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/book/{id}/comment/{comment_id}/delete")
    public String delete(@PathVariable("id") Integer id,
                         @PathVariable("comment_id") Integer commentId,
                         @CurrentUser CustomUserDetail customUserDetail,
                         RedirectAttributes redirectAttributes){
        Comment comment = commentService.findOne(commentId).get();
        User currentUser = userService.findByUserName(customUserDetail.getUsername());
        if ((comment.getUser().getUsername().equalsIgnoreCase(currentUser.getUsername()))
                || (currentUser.getUsername().equalsIgnoreCase("admin"))){
            commentService.delete(commentId);
            redirectAttributes.addFlashAttribute("success","Delete comment successfully");
            return "redirect:/book/{id}";
        } else {
            redirectAttributes.addFlashAttribute("fail","Cannot delete this comment");
            return "redirect:/book/{id}";
        }
    }
}
