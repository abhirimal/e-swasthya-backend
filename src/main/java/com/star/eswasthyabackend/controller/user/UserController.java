package com.star.eswasthyabackend.controller.user;

import com.star.eswasthyabackend.dto.user.UserRequestDto;
import com.star.eswasthyabackend.model.User;
import com.star.eswasthyabackend.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/add-new-user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin")
    public Integer addNewUser(@RequestBody UserRequestDto userRequestDto){
        return userService.addNewUser(userRequestDto);
    }
}
