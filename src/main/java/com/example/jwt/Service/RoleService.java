package com.example.jwt.Service;

import com.example.jwt.Dao.RoleDao;
import com.example.jwt.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {


    @Autowired
    private RoleDao roleDao;

    public Role creatNewRole(Role role){
        return roleDao.save(role);
    }
}
