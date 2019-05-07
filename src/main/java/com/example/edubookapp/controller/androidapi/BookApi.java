package com.example.edubookapp.controller.androidapi;


import com.example.edubookapp.model.*;
import com.example.edubookapp.payload.*;
import com.example.edubookapp.security.CurrentUser;
import com.example.edubookapp.security.CustomUserDetail;
import com.example.edubookapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/api/book")
public class BookApi {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private PendingCommentService pendingCommentService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeService likeService;

    @GetMapping("/{id}")
    public Map<String, Object> show(@PathVariable("id") Integer id,
                                    @CurrentUser CustomUserDetail customUserDetail) {
        List<CommentResponse> commentResponses = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (bookService.findOne(id).isPresent()) {
            Book book = bookService.findOne(id).get();
            if (!book.getComments().isEmpty()) {
                for (Comment comment : book.getComments()) {
                    commentResponses.add(new CommentResponse(comment.getId(), comment.getContent(), comment.getCreated(), comment.getUser().getUsername()));
                }
            }
            BookDetailResponse bookDetailResponse = new BookDetailResponse(book.getId(), book.getTitle(),
                    book.getAuthorName(), book.getPublisherName(),
                    book.getLanguage(), book.getBrief(), book.getPages(),
                    book.getImageLink(), book.getContentLink(), book.getLikes().size(),
                    commentResponses, book.getPublishDate());
            map.put("book", bookDetailResponse);
        } else map.put("book", "none");
        if (customUserDetail != null) {
            User user = userService.findByUserName(customUserDetail.getUsername());
            UserResponse userResponse = new UserResponse(user.getId(), user.getUsername());
            map.put("user", userResponse);
            if (likeService.findByBookIdAndUserId(id, user.getId()).isPresent()) {
                map.put("userLikedBook", "yes");
            } else {
                map.put("userLikedBook", "no");
            }
        } else {
            map.put("user", "none");
        }
        return map;
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @PostMapping("/{id}/comment")
    public ResponseEntity<?> postComment(@PathVariable("id") Integer id,
                                         @CurrentUser CustomUserDetail customUserDetail,
                                         @Valid @RequestBody CommentRequest commentRequest) {
        Book book = bookService.findOne(id).get();
        String content = commentRequest.getContent();
        PendingComment comment = new PendingComment();
        comment.setContent(content);
        comment.setCreated(new Date());
        comment.setBookname(book.getTitle());
        comment.setUsername(customUserDetail.getUsername());
        pendingCommentService.save(comment);
        return ResponseEntity.ok(new ApiResponse(true, "Your Comment is Pending"));
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @DeleteMapping("/{id}/comment/{commentId}/delete")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Integer id,
                                           @CurrentUser CustomUserDetail customUserDetail,
                                           @PathVariable("commentId") Integer commentId) {
        Comment comment = commentService.findOne(commentId).get();
        User currentUser = userService.findByUserName(customUserDetail.getUsername());
        if ((comment.getUser().getUsername().equalsIgnoreCase(currentUser.getUsername()))
                || (currentUser.getUsername().equalsIgnoreCase("admin"))) {
            commentService.delete(commentId);
            return ResponseEntity.ok(new ApiResponse(true, "Deleted comment!"));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Cannot delete this comment"));
        }
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/{id}/like")
    public ResponseEntity<?> likeBook(@PathVariable("id") Integer id,
                                      @CurrentUser CustomUserDetail customUserDetail) {
        Book book = bookService.findOne(id).get();
        User user = userService.findByUserName(customUserDetail.getUsername());
        Like like = new Like();
        like.setBook(book);
        like.setUser(user);
        likeService.save(like);
        return ResponseEntity.ok(new ApiResponse(true, "Liked"));
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @DeleteMapping("/{id}/like/delete")
    public ResponseEntity<?> deleteLike(@PathVariable("id") Integer id,
                                        @CurrentUser CustomUserDetail customUserDetail) {
        User user = userService.findByUserName(customUserDetail.getUsername());
        Like like = likeService.findByBookIdAndUserId(id, user.getId()).get();
        likeService.delete(like.getId());
        return ResponseEntity.ok(new ApiResponse(true, "Deleted like!"));
    }
}
