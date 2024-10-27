package com.example.javaprojectpudovkin.controller;

import com.example.javaprojectpudovkin.dto.RestDto;
import org.springframework.http.ResponseEntity;

public interface RequisitesController {

    String BASE_URL = "/api/v1/requisites";

    ResponseEntity<RestDto> getRestDto(Long id);
}
