package com.optimal.task.role;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepo roleRepo;


    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<RoleDTO> get(String str) {
        return null;
    }

}
