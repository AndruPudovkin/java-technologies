package com.example.javaprojectpudovkin.integration;

import com.example.javaprojectpudovkin.JavaProjectPudovkinApplication;
import com.example.javaprojectpudovkin.dto.RestDto;
import com.example.javaprojectpudovkin.integration.testContainers.PostgresTestContainers;
import com.example.javaprojectpudovkin.persistent.repository.RequisitesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@Transactional
@SpringBootTest(classes = JavaProjectPudovkinApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RequisitesServiceIntegrationTest extends PostgresTestContainers {

    @Autowired
    private RequisitesRepository requisitesRepository;

    private static final RestDto VALID_FUL_REST_DTO;
    private static final Long VALID_USER_ID = 1L;

    static {
        VALID_FUL_REST_DTO = new RestDto(
                "Платон",
                "52345678910111213141",
                "181920212"
        );
    }

    @Test
    public void testGetUserByID(){
        RestDto actualRestDto = requisitesRepository.findRestDtoById(VALID_USER_ID);
        assertEquals(actualRestDto, VALID_FUL_REST_DTO);
    }
}
