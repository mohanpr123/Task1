package com.example.Authentication.services;

import com.example.Authentication.dto.SignupRequest;
import com.example.Authentication.entity.User;

public interface AuthService  {

    User createUser(SignupRequest signupRequest);
}
