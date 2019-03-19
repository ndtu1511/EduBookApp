package com.example.edubookapp.service;

import com.example.edubookapp.model.Comment;
import com.example.edubookapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Comment> findOne(Integer id) {
        return commentRepository.findById(id);
    }
}
