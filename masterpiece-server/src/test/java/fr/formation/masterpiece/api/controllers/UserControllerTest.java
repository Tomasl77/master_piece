package fr.formation.masterpiece.api.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

import fr.formation.masterpiece.api.controllers.UserController;
import fr.formation.masterpiece.config.IntegrationTestConfig;
import fr.formation.masterpiece.domain.dtos.users.EntityUserViewDto;
import fr.formation.masterpiece.security.annotations.MockUserForTests;

class UserControllerTest extends IntegrationTestConfig {

    @Value("${mock.path.users}")
    private String path;

    @Autowired
    private UserController userController;

    @Test
    @MockUserForTests
    void should_get_by_id() {
	EntityUserViewDto tested = userController.getOne(1L);
	assertEquals("Tomas", tested.getUsername());
    }

    @Test
    void should_be_authorized() throws Exception {
	api.perform(get(path).header("Authorization", adminJohanna))
	        .andExpect(status().isOk());
    }

    @Test
    void should_be_forbidden_not_authorized() throws Exception {
	api.perform(get(path).header("Authorization", userTomas))
	        .andExpect(status().isForbidden());
    }

    @Test
    void should_be_forbidden_user_not_register() throws Exception {
	api.perform(patch(path).header("Authorization", userFalse))
	        .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usercontroller/CreateUserDto.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_create_user(String json) throws Exception {
	api.perform(post(path).contentType(MediaType.APPLICATION_JSON)
	        .content(json)).andExpect(status().isOk())
	        .andExpect(jsonPath("$.username").value("Thierry"))
	        .andExpect(jsonPath("$.email").value("thierry@gmail.com"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usercontroller/CreateUserDtoFalse.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should__not_create_user(String json) throws Exception {
	api.perform(post(path).contentType(MediaType.APPLICATION_JSON)
	        .content(json)).andExpect(status().isBadRequest());
    }

    // should check enable false
    @Test
    void should_admin_deactivate_user() throws Exception {
	api.perform(delete(path + "/3").header("Authorization", adminJohanna))
	        .andExpect(status().isOk());
    }

    @Test
    void should_user_not_deactivate_user() throws Exception {
	api.perform(delete(path + "/4").header("Authorization", userTomas))
	        .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usercontroller/UpdateUserDto.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_update_user(String json) throws Exception {
	api.perform(patch(path).header("Authorization", userTomas)
	        .contentType(MediaType.APPLICATION_JSON).content(json))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.email").value("new_tomas@gmail.com"));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usercontroller/UpdateUserDto.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_not_update_user(String json) throws Exception {
	api.perform(patch(path).header("Authorization", userTomas)
	        .contentType(MediaType.APPLICATION_JSON).content(json))
	        .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usercontroller/CheckEmailValid.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_be_valid_mail(String email) throws Exception {
	api.perform(get(path + "/" + email + "/mail-verify"))
	        .andExpect(jsonPath("$.isValid").value(true));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usercontroller/CheckEmailNotValid.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_be_not_valid_mail(String email) throws Exception {
	api.perform(get(path + "/" + email + "/mail-verify"))
	        .andExpect(jsonPath("$.isValid").value(false));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usercontroller/CheckUsernameNotValid.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_be_not_valid_username(String username) throws Exception {
	api.perform(get(path + "/" + username + "/verify"))
	        .andExpect(jsonPath("$.valid").value(false));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/usercontroller/CheckUsernameValid.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_be_valid_username(String usermane) throws Exception {
	api.perform(get(path + "/" + usermane + "/verify"))
	        .andExpect(jsonPath("$.valid").value(true));
    }
}
