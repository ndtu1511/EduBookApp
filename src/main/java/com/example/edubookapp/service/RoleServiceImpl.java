package com.example.edubookapp.service;

import com.example.edubookapp.model.Role;
import com.example.edubookapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Role findbyName(String name) {
        return roleRepository.findByName(name);
    }
}
