package com.example.javaprojectpudovkin.controller.impl;

import com.example.javaprojectpudovkin.controller.RequisitesController;
import com.example.javaprojectpudovkin.dto.RestDto;
import com.example.javaprojectpudovkin.service.RequisitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.javaprojectpudovkin.controller.RequisitesController.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class RequisitesControllerImpl implements RequisitesController {

    private final RequisitesService requisitesService;

    @Override
    @GetMapping({"restDto/{id}"})
    public ResponseEntity<RestDto> getRestDto(@PathVariable("id") Long id) {
        return ResponseEntity.ok(requisitesService.getRestDto(id));
    }
}
