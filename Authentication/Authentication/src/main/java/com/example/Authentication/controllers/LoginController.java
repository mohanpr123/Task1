package com.example.Authentication.controllers;

import com.example.Authentication.dto.LoginRequest;
import com.example.Authentication.dto.LoginResponse;
import com.example.Authentication.repository.UserRepository;
import com.example.Authentication.services.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserServiceImpl userService;
//    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public LoginController(UserServiceImpl userService,UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userService = userService;
//        this.authenticationManager=authenticationManager;
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws IOException
    {
//        try{
//            authenticationManager.authenticate
//                    (new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
//        }
//        catch (BadCredentialsException e){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad Credentials "+loginRequest.getEmail());
//    }

            if (userRepository.existsByEmail(loginRequest.getEmail())) {

                return ResponseEntity.status(HttpStatus.FOUND).body(new LoginResponse("Will generate Token","Login Successful"));
            }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("null","Bad credentials"));
    }
}
