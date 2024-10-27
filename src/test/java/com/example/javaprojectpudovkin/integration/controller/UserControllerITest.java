package com.example.javaprojectpudovkin.integration.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.javaprojectpudovkin.controller.UserController;
import com.example.javaprojectpudovkin.dto.UserFulInfoDto;
import com.example.javaprojectpudovkin.persistent.enam.Role;
import com.example.javaprojectpudovkin.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@WebMvcTest(UserController.class)
public class UserControllerITest {

    private static Long VALID_USER_ID = 1L;
    private static String VALID_URL = "/api/v1/users";
    private static UserFulInfoDto VALID_USER_FULL_INFO_DTO;
    private static UserFulInfoDto POST_USER_FULL_INFO_DTO;

    @MockBean
    private UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        VALID_USER_FULL_INFO_DTO = new UserFulInfoDto(
                "Платон",
                "Орлов",
                LocalDate.of( 2000,12,20),
                "494408658401",
                "66942669562",
                "3048118957",
                "platon",
                "12345",
                new HashSet<Role>(Set.of(Role.USER,Role.ADMIN)));

        POST_USER_FULL_INFO_DTO = new UserFulInfoDto(
                "John",
                "Doe",
                LocalDate.of(1990, 1, 1),
                "494408658401",
                "66942669562",
                "3048118957",
                "john",
                "john",
                new HashSet<Role>(Set.of(Role.USER,Role.ADMIN)));
    }

    @Test
    void getUserById_thenReturn200Ok() throws Exception {

        when(userService.getUserById(VALID_USER_ID)).thenReturn(VALID_USER_FULL_INFO_DTO);

        mockMvc.perform(MockMvcRequestBuilders.get(VALID_URL + "/" + VALID_USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(VALID_USER_FULL_INFO_DTO)));
    }

    @Test
    void createUser_thenReturn201Ok() throws Exception {

        String userJson = objectMapper.writeValueAsString(POST_USER_FULL_INFO_DTO);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("User created"));
    }


}
