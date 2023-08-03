package com.java.aop.controller;

import com.java.aop.aspect.Monitor;
import com.java.aop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.java.aop.model.User;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @Monitor
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(
            value = "/user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Monitor(isMonitoring = true)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.accepted().body(userRepository.save(user));
    }

}