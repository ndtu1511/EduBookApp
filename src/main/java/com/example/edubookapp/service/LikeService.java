package com.example.edubookapp.service;

import com.example.edubookapp.model.Like;

import java.util.Optional;

public interface LikeService {
    Like save(Like like);

    void delete(Integer id);

    Optional<Like> findOne(Integer id);

    Optional<Like> findByBookIdAndUserId(Integer bookId, Integer userId);
}