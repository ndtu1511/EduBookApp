package com.example.edubookapp.config;

import com.example.edubookapp.model.Role;
import com.example.edubookapp.model.User;
import com.example.edubookapp.repository.RoleRepository;
import com.example.edubookapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
        if (roleRepository.findByName("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER"));
        }
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setEmail("ndtu.1511@gmail.com");
            admin.addRole(roleRepository.findByName("ROLE_ADMIN"));
            admin.addRole(roleRepository.findByName("ROLE_MEMBER"));
            userRepository.save(admin);
        }
        if (userRepository.findByUsername("member") == null) {
            User member = new User();
            member.setUsername("member");
            member.setPassword(passwordEncoder.encode("123456"));
            member.setEmail("sample123@gmail.com");
            member.addRole(roleRepository.findByName("ROLE_MEMBER"));
            userRepository.save(member);
        }
    }
}
