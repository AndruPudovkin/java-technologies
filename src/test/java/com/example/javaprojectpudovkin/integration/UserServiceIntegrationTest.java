package com.example.javaprojectpudovkin.integration;

import com.example.javaprojectpudovkin.JavaProjectPudovkinApplication;
import com.example.javaprojectpudovkin.dto.UserFulInfoDto;
import com.example.javaprojectpudovkin.integration.testContainers.PostgresTestContainers;
import com.example.javaprojectpudovkin.persistent.enam.Role;
import com.example.javaprojectpudovkin.persistent.model.User;
import com.example.javaprojectpudovkin.persistent.repository.UserRepository;
import com.example.javaprojectpudovkin.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@Transactional
@SpringBootTest(classes = JavaProjectPudovkinApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceIntegrationTest extends PostgresTestContainers {

        @Autowired
        private UserService userDaoService;

        @Autowired
        private UserRepository userDaoRepository;

        private static final UserFulInfoDto VALID_FUL_INFO_DTO;
        private static final UserFulInfoDto VALID_FUL_INFO_DTO_SAVE;
        private static final Long VALID_USER_ID = 1L;

        static {
            VALID_FUL_INFO_DTO = new UserFulInfoDto(
                    "Платон",
                    "Орлов",
                    LocalDate.of( 2000,12,20),
                    "494408658401",
                    "66942669562",
                    "3048118957",
                    "platon",
                    "12345",
                    new HashSet<Role>(Set.of(Role.USER,Role.ADMIN)));

            VALID_FUL_INFO_DTO_SAVE = new UserFulInfoDto(
                    "Иван",
                    "Иванов",
                    LocalDate.of( 2000,12,20),
                    "944480777410",
                    "76942669526",
                    "0348118975",
                    "test",
                    "00000",
                    new HashSet<Role>(Set.of(Role.USER)));
        }

        @Test
        public void testGetUserByID(){
            UserFulInfoDto actualUserFulInfoDto = userDaoService.getUserById(VALID_USER_ID);
            assertEquals(actualUserFulInfoDto, VALID_FUL_INFO_DTO);
        }

        @Test
        public void testSaveUser(){
            userDaoService.saveUser(VALID_FUL_INFO_DTO_SAVE);

            User saveUser = userDaoRepository.findUserByLogin(VALID_FUL_INFO_DTO_SAVE.login());

            assertNotNull(saveUser);
            assertEquals(VALID_FUL_INFO_DTO_SAVE.lastName(), saveUser.getLastName());
            assertEquals(VALID_FUL_INFO_DTO_SAVE.firstName(), saveUser.getFirstName());
            assertEquals(VALID_FUL_INFO_DTO_SAVE.dateOfBirth(), saveUser.getDateOfBirth());
            assertEquals(VALID_FUL_INFO_DTO_SAVE.inn(), saveUser.getInn());
            assertEquals(VALID_FUL_INFO_DTO_SAVE.snils(), saveUser.getSnils());
            assertEquals(VALID_FUL_INFO_DTO_SAVE.passportNumber(), saveUser.getPassportNumber());
            assertEquals(VALID_FUL_INFO_DTO_SAVE.login(), saveUser.getLogin());
            assertEquals(VALID_FUL_INFO_DTO_SAVE.password(), saveUser.getPassword());
        }
}
