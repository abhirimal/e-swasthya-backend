package com.star.eswasthyabackend.controller.user;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.user.UserRequestDto;
import com.star.eswasthyabackend.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sign-up")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/admin")
    public ResponseEntity<ApiResponse> addNewUser(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(new ApiResponse(true, "User Registered successfully",userService.addNewUser(userRequestDto)));
    }

    @GetMapping("/verify-account/{id}/{token}")
    public ResponseEntity<ApiResponse> verifyUserAccount(@PathVariable Integer id, @PathVariable String token){

        return ResponseEntity.ok(new ApiResponse(true, "Account Verified Successfully.",userService.verifyAccount(id, token)));
    }

    @GetMapping("/dashboard")
    public String helloWorld(){
        return "Hello World";
    }


}
