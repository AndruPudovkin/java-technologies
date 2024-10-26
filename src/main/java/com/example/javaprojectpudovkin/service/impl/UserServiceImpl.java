package com.example.javaprojectpudovkin.service.impl;

import com.example.javaprojectpudovkin.dto.UserFulInfoDto;
import com.example.javaprojectpudovkin.mapper.UserMapper;
import com.example.javaprojectpudovkin.persistent.model.User;
import com.example.javaprojectpudovkin.persistent.model.UserRole;
import com.example.javaprojectpudovkin.persistent.repository.UserRepository;
import com.example.javaprojectpudovkin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserFulInfoDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void saveUser(UserFulInfoDto userFulInfoDto) {
        Set<UserRole> roles = new HashSet<>();
        User user = userMapper.toEntity(userFulInfoDto);

        userFulInfoDto.roles().stream().forEach(role -> {
            UserRole userRole = new UserRole();
            userRole.setUserRole(role);
            userRole.setUser(user);
            roles.add(userRole);
        });
        user.setRoles(roles);
        userRepository.save(user);
    }
}
