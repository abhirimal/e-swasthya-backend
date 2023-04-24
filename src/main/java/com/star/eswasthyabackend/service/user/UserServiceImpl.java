package com.star.eswasthyabackend.service.user;

import com.star.eswasthyabackend.dto.user.UserRequestDto;
import com.star.eswasthyabackend.model.Role;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.repository.RoleRepository;
import com.star.eswasthyabackend.repository.UserRepository;
import com.star.eswasthyabackend.security.SecurityConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SecurityConfiguration securityConfiguration;

    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, SecurityConfiguration securityConfiguration, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.securityConfiguration = securityConfiguration;
        this.roleRepository = roleRepository;
    }

    @Override
    public Integer addNewUser(UserRequestDto userRequestDto) {
        User newUser = new User();
        newUser.setId(userRequestDto.getId());
        newUser.setPassword(securityConfiguration.getPasswordEncoder().encode(userRequestDto.getPassword()));
        newUser.setUsername(userRequestDto.getUsername());
        newUser.setIsVerified(false);

        List<Role> roleList = new ArrayList<>();

        for(Integer roleId : userRequestDto.getRolesId()){
            Role role = roleRepository.findById(roleId).orElse(null);
            roleList.add(role);
        }

        newUser.setRoles(roleList);
        userRepository.saveAndFlush(newUser);

        return newUser.getId();

//        for (Integer roleId : userRequestDto.getRolesId()) {
//            Role role = new Role();
//            if (roleId == Role.ROLE_ID_ADMIN) {
//                role.setRoleName(Role.ROLE_ADMIN);
//            } else if (roleId == Role.ROLE_ID_PATIENT) {
//                role.setRoleName(Role.ROLE_PATIENT);
//            } else if (roleId == Role.ROLE_ID_DOCTOR) {
//                role.setRoleName((Role.ROLE_DOCTOR));
//            } else if (roleId == Role.ROLE_ID_SUPPORT) {
//                role.setRoleName(Role.ROLE_SUPPORT);
//            } else {
//                throw new RuntimeException("Unknown Role Name");
//            }
//            roleList.add(role);
//        }
    }
}
