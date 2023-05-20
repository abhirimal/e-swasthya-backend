package com.star.eswasthyabackend.controller.auth;

import com.star.eswasthyabackend.dto.JwtResponse;
import com.star.eswasthyabackend.dto.login.UserLoginRequest;
import com.star.eswasthyabackend.exception.AppException;
import com.star.eswasthyabackend.utility.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginRequest loginRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(jwt));
        }
        catch (AuthenticationException e){
            throw new AppException("Incorrect email or password", HttpStatus.BAD_REQUEST);
        }

    }

}
