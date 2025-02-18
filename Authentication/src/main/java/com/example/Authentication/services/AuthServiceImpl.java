package com.example.Authentication.services;

import com.example.Authentication.dto.SignupRequest;
import com.example.Authentication.entity.User;
import com.example.Authentication.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    public User createUser(SignupRequest signupRequest) {
        //Check if User Already exits or not
        if(userRepository.existsByEmail(signupRequest.getEmail())){
            return null;
        }
        User user =new User();
        BeanUtils.copyProperties(signupRequest,user);


        String HashPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(HashPassword);
        User created = userRepository.save(user);
        user.setId(created.getId());

        return user;
    }
}
