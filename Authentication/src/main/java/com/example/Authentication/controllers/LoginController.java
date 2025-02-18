package com.example.Authentication.controllers;

import com.example.Authentication.dto.LoginRequest;
import com.example.Authentication.dto.LoginResponse;
import com.example.Authentication.repository.UserRepository;
import com.example.Authentication.services.UserServiceImpl;
import com.example.Authentication.utility.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;




    @Autowired
    public LoginController(UserServiceImpl userService,AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws IOException
    {
        try{
            authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
//            if (userRepository.existsByEmail(loginRequest.getEmail())) {
//
//                return ResponseEntity.status(HttpStatus.FOUND).body(new LoginResponse("Will generate Token","Login Successful"));
//            }
        }
        catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad credentials : "+loginRequest.getEmail());
        }

        final UserDetails user= userService.loadUserByUsername(loginRequest.getEmail());
        final String jwt =jwtUtil.generateToken(String.valueOf(user.getUsername()));

        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}
