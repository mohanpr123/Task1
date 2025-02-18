package com.example.Authentication.repository;

import com.example.Authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yaml.snakeyaml.events.Event;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByEmail(String email);

//    @Query("SELECT *FROM users WHERE user.password = :password")
//    Optional<User> findByPassword(@Param("password") String password);

    Optional<User> findByEmail(String email);
}
