package com.example.edubookapp.service;

import com.example.edubookapp.model.Like;
import com.example.edubookapp.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Override
    @Transactional
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        likeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Like> findOne(Integer id) {
        return likeRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Like> findByBookIdAndUserId(Integer bookId, Integer userId) {
        return likeRepository.findByBookIdAndUserId(bookId, userId);
    }


}
