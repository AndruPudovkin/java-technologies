package com.example.javaprojectpudovkin.integration.testContainers;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class PostgresTestContainers {

    private static String CLASSPATH_MIGRATION = "db.changelog/db.changelog-master.yaml";
    private static String DATA_BASE_NAME = "usersdb";
    private static String USER_NAME = "developer";
    private static String PASSWORD = "pass";

    @Container
    public static PostgreSQLContainer<?> POSTGRESQL_CONTAINER;

    static {
        POSTGRESQL_CONTAINER = new PostgreSQLContainer("postgres:latest")
                .withDatabaseName(DATA_BASE_NAME)
                .withUsername(USER_NAME)
                .withPassword(PASSWORD);
        POSTGRESQL_CONTAINER.start();
    }
    @DynamicPropertySource
    public static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);

        registry.add("spring.liquibase.url",POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("spring.liquibase.user",  POSTGRESQL_CONTAINER::getUsername);
        registry.add("spring.liquibase.password", POSTGRESQL_CONTAINER::getPassword);
        registry.add("spring.liquibase.change-log", () -> CLASSPATH_MIGRATION);
    }
}
