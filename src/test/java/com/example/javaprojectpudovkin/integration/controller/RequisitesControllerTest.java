package com.example.javaprojectpudovkin.integration.controller;

import com.example.javaprojectpudovkin.controller.RequisitesController;
import com.example.javaprojectpudovkin.dto.RestDto;
import com.example.javaprojectpudovkin.service.RequisitesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RequisitesController.class)
public class RequisitesControllerTest {

    private static Long VALID_USER_ID = 1L;
    private static String VALID_URL = "/api/v1/requisites";
    private static RestDto VALID_REST_DTO;

    @MockBean
    private RequisitesService requisitesService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        VALID_REST_DTO = new RestDto(
                "Платон",
                "52345678910111213141",
                "181920212");
    }

    @Test
    void getRestDto_thenReturn200Ok() throws Exception {

        when(requisitesService.getRestDto(VALID_USER_ID)).thenReturn(VALID_REST_DTO);

        mockMvc.perform(MockMvcRequestBuilders.get(VALID_URL + "/restDto/" + VALID_USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(VALID_REST_DTO)));
    }
}
