package com.example.javaprojectpudovkin.service.impl;

import com.example.javaprojectpudovkin.dto.RestDto;
import com.example.javaprojectpudovkin.persistent.repository.RequisitesRepository;
import com.example.javaprojectpudovkin.service.RequisitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RequisitesServiceImpl implements RequisitesService {

    private final RequisitesRepository requisitesRepository;

    @Override
    @Transactional
    public RestDto getRestDto(Long id) {
        return requisitesRepository.findRestDtoById(id);
    }
}
