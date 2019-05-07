package com.example.edubookapp.service;

import com.example.edubookapp.model.Comment;
import com.example.edubookapp.model.PendingComment;
import com.example.edubookapp.repository.BookRepository;
import com.example.edubookapp.repository.CommentRepository;
import com.example.edubookapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
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

    @Override
    public Comment register(PendingComment pendingComment) {
        Comment comment = new Comment();
        comment.setUser(userRepository.findByUsername(pendingComment.getUsername()));
        comment.setBook(bookRepository.findByTitle(pendingComment.getBookname()));
        comment.setCreated(pendingComment.getCreated());
        comment.setContent(pendingComment.getContent());
        return commentRepository.save(comment);
    }
}
