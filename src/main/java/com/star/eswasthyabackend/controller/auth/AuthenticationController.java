package com.star.eswasthyabackend.controller.auth;

import com.star.eswasthyabackend.dto.JwtResponse;
import com.star.eswasthyabackend.dto.login.UserLoginRequest;
import com.star.eswasthyabackend.exception.BadCredentialsException;
import com.star.eswasthyabackend.utility.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody UserLoginRequest loginRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(jwt));
        }
        catch (AuthenticationException e){
            throw new BadCredentialsException("Incorrect username or password");
        }

    }

    @GetMapping("/dashboard")
    public String helloWorld(){
        return "Hello World";
    }
}
