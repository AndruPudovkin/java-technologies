package com.example.javaprojectpudovkin.controller;

import com.example.javaprojectpudovkin.dto.UserFulInfoDto;
import org.springframework.http.ResponseEntity;

public interface UserController {
    String BASE_URL = "/api/v1/users";

    ResponseEntity<UserFulInfoDto> getUserById(Long id);

    ResponseEntity<String> createUser(UserFulInfoDto userFulInfoDto);
}
