package com.example.javaprojectpudovkin.dto;

import com.example.javaprojectpudovkin.persistent.enam.Role;

import java.time.LocalDate;
import java.util.Set;

public record UserFulInfoDto(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String inn,
        String snils,
        String passportNumber,
        String login,
        String password,
        Set<Role> roles) {
}
