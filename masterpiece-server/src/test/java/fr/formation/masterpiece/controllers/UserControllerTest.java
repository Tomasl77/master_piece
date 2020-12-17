package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private static final String ADMIN = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsInVzZXJfbmFtZSI6IkpvaGFubmEiLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6ImFiN2U4MTgyLWE2OWMtNDc1ZC05YTJiLWVhMDgwYWM5NTA2MCIsImNsaWVudF9pZCI6Im1hc3RlcnBpZWNlLXdlYiIsInNjb3BlIjpbInRydXN0ZWQiXX0.WLV4NjYtN82FehMS71CLVkuN8pITTMWb2htcNiRrAEKCLgwd_EHZT4M3APHWkzZ1HyTHMWStCHiXLtzhuxW8loPetox-lbK7IaD79H6F8zUc1SUf2qRYb6nk7GhvJ-7wUZcxqjrwHWoOlI-Z1HBw3o1Cc6ERlFi0C7IGUksDoNOMOJhP2G8HQGI3fRmKatYVchKzj7SGdRNv_0X5KmzEJTOkJAAJvqQ0pCXVOhSu24-YBuTSXXX9M0vUP_GIL4ust4hsOX32qlq5ZkkMBu0W1kCrNwZahz5Vx7WZ2lCOuYGmug_k03hW9MPjCXJk0ooBqev_iUdeXaNZsCM2m7wF6w";

    private static final String USER = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsInVzZXJfbmFtZSI6IlRvbWFzIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjdjMGRjNGI4LWZmZTAtNGFiZC05NTJlLTQ4NmViZmUwYmFlYyIsImNsaWVudF9pZCI6Im1hc3RlcnBpZWNlLXdlYiIsInNjb3BlIjpbInRydXN0ZWQiXX0.clFYA8R87fOUULGj7U4q2G9n4iQ9Udp6pTS_dQuP0q1A2ipOpWl3V80kIuair6i7wKzdKR0X-lpy6SREzwy0m_X6HO56QEEz9NJsIR5gJ-4jb4OCZJdo2laNGN6hpbjm7D8UhFCYyBR7XdrPE1LvIgUtUsDBz272w7DmWyKUoggOpvURX2YrQY0quARkcnU6TUWaOEr97yWbjiat_mMasHGY_UtoNU32qF9cHIwVLI1w6NFhPRvfxqQtsb5oV1xQCojyb9wXPOZ5D6u54cxNdOshz4gUTsNZUrTE1SfyhfUb959zxyfkUxKnfEaVOaDUskiEx92ROzOIYlg60tBpWA";

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
	api.perform(get("/api/users").header("Authorization", ADMIN))
	        .andExpect(status().isOk());
    }

    @Test
    void should_be_forbidden() throws Exception {
	api.perform(get("/api/users").header("Authorization", USER))
	        .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/CreateUserDto.csv", delimiter = ';',
            numLinesToSkip = 1)
    void should_create_user_mock_mvc(String json) throws Exception {
	api.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
	        .content(json)).andExpect(status().isOk());
    }
}
