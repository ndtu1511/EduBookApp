package com.example.edubookapp.controller.androidapi;

import com.example.edubookapp.model.Comment;
import com.example.edubookapp.model.Download;
import com.example.edubookapp.model.Like;
import com.example.edubookapp.model.User;
import com.example.edubookapp.payload.*;
import com.example.edubookapp.security.CurrentUser;
import com.example.edubookapp.security.CustomUserDetail;
import com.example.edubookapp.service.DownloadService;
import com.example.edubookapp.service.UserService;
import com.example.edubookapp.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_MEMBER')")
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private DownloadService downloadService;

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
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse(false, "There is no book"));
    }

    @GetMapping("/{id}")
    public UserDetailResponse findUser(@PathVariable Integer id) {
        User user = userService.findOne(id).get();
        UserDetailResponse userDetailResponse = new UserDetailResponse(user.getId(), user.getUsername(), user.getEmail(), user.getComments().size());
        return userDetailResponse;
    }

    @GetMapping("/download")
    public ResponseEntity<?> downloadResponse(@CurrentUser CustomUserDetail customUserDetail) {
        User user = userService.findByUserName(customUserDetail.getUsername());
        List<DownloadResponse> downloadResponses = new ArrayList<>();
        if (!user.getDownloads().isEmpty()) {
            for (Download download : user.getDownloads()) {
                downloadResponses.add(new DownloadResponse(download.getId(), user.getUsername(), download.getBook().getTitle(), download.getCurrentPage(), download.getDownloadDate()));
            }
            return ResponseEntity.ok(downloadResponses);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse(false, "There is no save"));
    }

    @PostMapping("/avatar/upload")
    public ResponseEntity<?> postAvatar(@CurrentUser CustomUserDetail customUserDetail,
                                        @RequestParam("file") MultipartFile multipartFile) {
        User user = userService.findByUserName(customUserDetail.getUsername());
        if (multipartFile.isEmpty()) {
            return ResponseEntity.ok(new ApiResponse(true, "please select a file"));
        }
        userService.uploadAvatar(user, multipartFile);
        return ResponseEntity.ok(new ApiResponse(true, "Successfully uploaded - " + multipartFile.getOriginalFilename()));
    }

    @DeleteMapping("/avatar/delete")
    public ResponseEntity<?> deleteAvatar(@CurrentUser CustomUserDetail customUserDetail) {
        User user = userService.findByUserName(customUserDetail.getUsername());
        Path deletePath = Paths.get(Const.UPLOAD_AVATAR + "/" + user.getImageLink());
        user.deleteImage();
        try {
            Files.delete(deletePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userService.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "Delete avatar successfully"));
    }

    @GetMapping("/comment")
    public ResponseEntity<?> allComment(@CurrentUser CustomUserDetail customUserDetail) {
        List<CommentResponseUser> commentResponseUsers = new ArrayList<>();
        User user = userService.findByUserName(customUserDetail.getUsername());
        if (user.getComments().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse(false, "there is no comment"));
        }
        for (Comment comment : user.getComments()) {
            commentResponseUsers.add(new CommentResponseUser(comment.getId(),
                    comment.getContent(), comment.getCreated(), comment.getBook().getTitle()));
        }
        return ResponseEntity.ok(commentResponseUsers);
    }

    @DeleteMapping("/download/{id}/delete")
    public ResponseEntity<?> deleteDownload(@PathVariable Integer id, @CurrentUser CustomUserDetail customUserDetail) {
        User user = userService.findByUserName(customUserDetail.getUsername());
        Download download = downloadService.findById(id).get();
        if (download.getUser().getId() != customUserDetail.getId()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse(false, "You can not delete this"));
        }
        downloadService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "delete successfully"));
    }
}
