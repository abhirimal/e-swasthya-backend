package com.star.eswasthyabackend.controller.user;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.user.UserRequestDto;
import com.star.eswasthyabackend.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> addNewUser(@Valid @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(new ApiResponse(true, "User Registered successfully",userService.addNewUser(userRequestDto)));
    }

    @GetMapping("/verify-account/{id}/{token}")
    public ResponseEntity<ApiResponse> verifyUserAccount(@PathVariable Integer id, @PathVariable String token){

        return ResponseEntity.ok(new ApiResponse(true, "Account Verified Successfully.",userService.verifyAccount(id, token)));
    }

    @GetMapping("/verification-resend-email/{id}")
    public ResponseEntity<?> resendVerificationLink(@PathVariable Integer id){
        userService.resendVerificationLink(id);
        return ResponseEntity.ok(new ApiResponse(true, "Verification email sent successfully.", null));
    }

    @GetMapping("/dashboard")
    public String helloWorld(){
        return "Hello World";
    }


}
