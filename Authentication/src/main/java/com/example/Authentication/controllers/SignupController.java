package com.example.Authentication.controllers;

import com.example.Authentication.dto.SignupRequest;
import com.example.Authentication.entity.User;
import com.example.Authentication.repository.UserRepository;
import com.example.Authentication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignupController {

    private final AuthService authService;

    @Autowired
    public SignupController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest)
    {
//        try {
//            User createdUser = authService.createUser(signupRequest);
//                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
        User createdUser = authService.createUser(signupRequest);
        if(createdUser!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exists");
        }
    }
}
