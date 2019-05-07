package com.example.edubookapp.controller.androidapi;

import com.example.edubookapp.model.Like;
import com.example.edubookapp.model.User;
import com.example.edubookapp.payload.BookIntroduce;
import com.example.edubookapp.payload.UserDetailResponse;
import com.example.edubookapp.security.CurrentUser;
import com.example.edubookapp.security.CustomUserDetail;
import com.example.edubookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_MEMBER')")
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    private UserService userService;

    @GetMapping
    public UserDetailResponse show(@CurrentUser CustomUserDetail customUserDetail) {
        User user = userService.findByUserName(customUserDetail.getUsername());
        UserDetailResponse userDetailResponse = new UserDetailResponse(user.getId(), user.getUsername(), user.getEmail(), user.getComments().size());
        return userDetailResponse;
    }

    @GetMapping("/book/like")
    public ResponseEntity<?> showBookLiked(@CurrentUser CustomUserDetail customUserDetail) {
        User user = userService.findByUserName(customUserDetail.getUsername());
        List<BookIntroduce> bookIntroduces = new ArrayList<>();
        if (!user.getLikes().isEmpty()) {
            for (Like like : user.getLikes()) {
                bookIntroduces.add(new BookIntroduce(like.getBook().getId(), like.getBook().getTitle(), like.getBook().getImageLink()));
            }
            return ResponseEntity.ok(bookIntroduces);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("There is no book");
    }

    @GetMapping("/{id}")
    public UserDetailResponse findUser(@PathVariable Integer id) {
        User user = userService.findOne(id).get();
        UserDetailResponse userDetailResponse = new UserDetailResponse(user.getId(), user.getUsername(), user.getEmail(), user.getComments().size());
        return userDetailResponse;
    }
}
