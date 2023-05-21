package com.star.eswasthyabackend.controller.user;

import com.star.eswasthyabackend.dto.ApiResponse;
import com.star.eswasthyabackend.dto.user.UserResetPasswordRequest;
import com.star.eswasthyabackend.dto.user.UserSignUpRequest;
import com.star.eswasthyabackend.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> addNewUser(@Valid @RequestBody UserSignUpRequest userSignUpRequest){
        return ResponseEntity.ok(new ApiResponse(true, "User Registered successfully",userService.addNewUser(userSignUpRequest)));
    }

    @GetMapping("/verify-account/{id}/{token}")
    public ResponseEntity<ApiResponse> verifyUserAccount(@PathVariable Integer id, @PathVariable String token){

        return ResponseEntity.ok(new ApiResponse(true, "Account Verified Successfully.",userService.verifyAccount(id, token)));
    }

    @GetMapping("/verification-resend-email/{id}")
    public ResponseEntity<?> resendVerificationLink(@PathVariable Integer id){
        userService.resendVerificationLink(id);
        return ResponseEntity.ok(new ApiResponse(true, "Verification link is sent in your mail successfully.", null));
    }

    @GetMapping("reset-password-request/{email}")
    public ResponseEntity<?> resetPasswordRequest(@Email(message = "good email") @PathVariable String email){
        userService.resetPasswordRequest(email);
        return ResponseEntity.ok(new ApiResponse(true, "Reset password link is sent in your mail successfully", null));
    }

    @GetMapping("verify-reset-password-link/{id}/{token}")
    public ResponseEntity<?> verifyResetPasswordLink(@PathVariable Integer id, @PathVariable String token){

        return ResponseEntity.ok(new ApiResponse(true,
                "Token validated successfully.", userService.verifyResetPasswordLink(id, token)));
    }

    @PostMapping("change-password")
    public ResponseEntity<?> changePassword(@RequestBody UserResetPasswordRequest passwordRequest){
        userService.changePassword(passwordRequest);
        return ResponseEntity.ok(new ApiResponse(true,"Password changed Successfully.", null));
    }

    @GetMapping("/dashboard")
    public String helloWorld(){
        return "Hello World";
    }


}
