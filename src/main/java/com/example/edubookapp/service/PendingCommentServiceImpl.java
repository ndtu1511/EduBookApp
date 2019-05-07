package com.example.edubookapp.service;

import com.example.edubookapp.model.PendingComment;
import com.example.edubookapp.repository.PendingCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PendingCommentServiceImpl implements PendingCommentService {
    @Autowired
    private PendingCommentRepository pendingCommentRepository;

    @Override
    @Transactional
    public PendingComment save(PendingComment pendingComment) {
        return pendingCommentRepository.save(pendingComment);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        pendingCommentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<PendingComment> findOne(Integer id) {
        return pendingCommentRepository.findById(id);
    }

    @Override
    public Iterable<PendingComment> findAll() {
        return pendingCommentRepository.findAll();
    }
}
