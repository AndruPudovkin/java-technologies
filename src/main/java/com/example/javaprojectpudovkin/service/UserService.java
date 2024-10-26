package com.example.javaprojectpudovkin.service;

import com.example.javaprojectpudovkin.dto.UserFulInfoDto;

public interface UserService {

    UserFulInfoDto getUserById(Long id);

    void saveUser(UserFulInfoDto userFulInfoDto);
}
