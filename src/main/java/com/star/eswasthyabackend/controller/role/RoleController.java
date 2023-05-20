package com.star.eswasthyabackend.controller.role;

import com.star.eswasthyabackend.model.Role;
import com.star.eswasthyabackend.service.role.RoleService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
@CrossOrigin("*")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add-new-role")
    public Integer addNewRoles(@RequestBody Role role){
        return roleService.addNewRole(role);

    }
}
