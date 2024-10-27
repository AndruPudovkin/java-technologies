package com.example.javaprojectpudovkin.controller.impl;

import com.example.javaprojectpudovkin.controller.UserController;
import com.example.javaprojectpudovkin.dto.UserFulInfoDto;
import com.example.javaprojectpudovkin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.javaprojectpudovkin.controller.UserController.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserFulInfoDto> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserFulInfoDto userFulInfoDto) {
        userService.saveUser(userFulInfoDto);
        return ResponseEntity.ok("User created");
    }
}
