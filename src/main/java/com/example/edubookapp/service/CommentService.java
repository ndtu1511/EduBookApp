package com.example.edubookapp.service;

import com.example.edubookapp.model.Comment;
import com.example.edubookapp.model.PendingComment;

import java.util.Optional;

public interface CommentService {
    Comment save(Comment comment);
    void delete(Integer id);
    Optional<Comment> findOne(Integer id);

    Comment register(PendingComment pendingComment);
}
