package com.example.jwt.Controller;

import com.example.jwt.Model.Role;
import com.example.jwt.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/creatNewRole")
    public Role creatNewRole(@RequestBody Role role){
        return roleService.creatNewRole(role);
    }
}
