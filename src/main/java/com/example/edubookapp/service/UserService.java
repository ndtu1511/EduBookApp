package com.example.edubookapp.service;

import com.example.edubookapp.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Iterable<User> findAll();
    List<User> search(String s);
    User register (User user);
    User save (User user);
    User findByUserName(String username);
    User findByEmail(String email);
    long countAll();
    void delete(User user);
    Optional<User> findOne(Integer id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    User uploadAvatar(User user, MultipartFile multipartFile);
}
