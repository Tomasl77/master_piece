package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import fr.formation.masterpiece.api.controllers.UserController;
import fr.formation.masterpiece.config.IntegrationTestConfig;
import fr.formation.masterpiece.domain.dtos.users.CustomUserCreateDto;
import fr.formation.masterpiece.domain.dtos.users.CustomUserViewDto;
import fr.formation.masterpiece.security.annotations.MockAdminForTests;
import fr.formation.masterpiece.security.annotations.MockUserForTests;

class UserControllerTest extends IntegrationTestConfig {

    @Autowired
    private UserController userController;

    @Test
    @MockUserForTests
    void should_get_by_id() {
	CustomUserViewDto tested = userController.getOne(1L);
	assertEquals("Tomas", tested.getUsername());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/CreateUserDto.csv", delimiter = ';',
            numLinesToSkip = 1)
    @MockAdminForTests
    void should_create_user(String json) {
	int actual = userController.getAll().size();
	CustomUserCreateDto dto = jsonConvert(json, CustomUserCreateDto.class);
	userController.create(dto);
	int expected = userController.getAll().size();
	assertEquals(expected, actual + 1);
    }

    @Test
    void should_be_authorized() throws Exception {
	api.perform(get("/api/users").header("Authorization", admin))
	        .andExpect(status().isOk());
    }

    @Test
    void should_be_forbidden() throws Exception {
	api.perform(get("/api/users").header("Authorization", user))
	        .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/CreateUserDto.csv", delimiter = ';',
            numLinesToSkip = 1)
    void should_create_user_mock_mvc(String json) throws Exception {
	api.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
	        .content(json)).andExpect(status().isOk())
	        .andExpect(jsonPath("$.username").value("Thierry"))
	        .andExpect(jsonPath("$.email").value("thierry@gmail.com"));
    }
}
