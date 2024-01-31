package com.example.ra.Service;

import com.example.ra.model.entity.Role;

import java.util.List;

public interface IRoleService {
    Role findByRoleName(String roleName);

    List<Role> findAll();
}
