package com.example.ra.Service.Imp;

import com.example.ra.Service.IRoleService;
import com.example.ra.model.entity.Role;
import com.example.ra.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByName(roleName).orElseThrow(()->new RuntimeException("Role Not Found"));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }


}
