package org.example.controller;

import org.example.entity.UserData;
import org.example.model.dto.UserCreateDto;
import org.example.model.dto.UserUpdateDto;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("authentication")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserCreateDto user) {
        userService.create(new UserData(user.getLogin(), user.getPassword()));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public void updateRole(@RequestBody UserUpdateDto userUpdateDto) {
        userService.updateRole(userUpdateDto.getLogin());
    }
}