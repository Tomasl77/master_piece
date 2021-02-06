package fr.formation.masterpiece.api.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

import fr.formation.masterpiece.config.IntegrationTestConfig;

class SharingSessionControllerTest extends IntegrationTestConfig {

    // post et get
    @Value("${mock.path.sessions}")
    private String path;

    @Test
    void should_user_get_all_sessions() throws Exception {
	api.perform(get(path).header("Authorization", userTomas))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.[0].subjectTitle").value("Spring"));
    }

    @Test
    void should_admin_get_all_sessions() throws Exception {
	api.perform(get(path).header("Authorization", adminJohanna))
	        .andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sessioncontroller/CreateSessionDto.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_admin_create_session(String json) throws Exception {
	api.perform(post(path).header("Authorization", adminJohanna)
	        .contentType(MediaType.APPLICATION_JSON).content(json))
	        .andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/sessioncontroller/CreateSessionDtoSameDay.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should__user_not_create_session_same_day_validator(String json)
            throws Exception {
	api.perform(post(path).header("Authorization", userTomas)
	        .contentType(MediaType.APPLICATION_JSON).content(json))
	        .andExpect(status().isBadRequest());
    }
}
