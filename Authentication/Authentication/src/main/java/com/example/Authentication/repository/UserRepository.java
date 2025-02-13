package com.example.Authentication.repository;

import com.example.Authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);
//    boolean exitsByPassword(String password);

    Optional<User> findByEmail(String email);
}
