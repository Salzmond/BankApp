package org.example.controller;

import org.example.entity.UserData;
import org.example.model.dto.UserCreateDto;
import org.example.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authentication")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserCreateDto user) {
        authService.create(new UserData(user.getLogin(), user.getPassword()));
    }
}