package com.example.edubookapp.service;

import com.example.edubookapp.security.CustomUserDetail;
import com.example.edubookapp.model.Role;
import com.example.edubookapp.model.User;
import com.example.edubookapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public CustomUserDetail loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail);
        if (user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return CustomUserDetail.create(user);
    }
    // This method is used by JWTAuthenticationFilter
    @Transactional
    public CustomUserDetail loadUserById(int id){
        User user  = userRepository.findById(id);
        if (user==null){
            throw new UsernameNotFoundException("User not found with id:"+id);
        }
        return CustomUserDetail.create(user);
    }
}
