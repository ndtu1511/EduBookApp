package com.example.edubookapp.service;

import com.example.edubookapp.model.PendingComment;

import java.util.Optional;

public interface PendingCommentService {
    PendingComment save(PendingComment pendingComment);

    void delete(Integer id);

    Optional<PendingComment> findOne(Integer id);

    Iterable<PendingComment> findAll();
}
