package com.star.eswasthyabackend.service.role;

import com.star.eswasthyabackend.model.Role;
import com.star.eswasthyabackend.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository repository) {
        this.roleRepository = repository;
    }

    @Override
    public Integer addNewRole(Role role) {
        Role newRole = new Role();
        newRole.setRoleName(role.getRoleName());
        roleRepository.save(newRole);

        return newRole.getRoleId();
    }
}
