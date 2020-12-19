package fr.formation.masterpiece.api.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

import fr.formation.masterpiece.api.controllers.SubjectController;
import fr.formation.masterpiece.config.IntegrationTestConfig;

class SubjectControllerTest extends IntegrationTestConfig {

    @Value("${mock.path.subjects}")
    private String path;

    @Autowired
    private SubjectController subjectController;

    @Test
    void should_admin_delete_subject() throws Exception {
	api.perform(delete(path + "/10").header("Authorization", adminJohanna))
	        .andExpect(status().isNoContent());
    }

    @Test
    void should_user_not_delete_subject() throws Exception {
	api.perform(delete(path + "/11").header("Authorization", userTomas))
	        .andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/subjectcontroller/CreateSubjectDto.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_user_create_subject(String json) throws Exception {
	api.perform(post(path).header("Authorization", userTomas)
	        .contentType(MediaType.APPLICATION_JSON).content(json))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.title").value("Integration Test"));
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/subjectcontroller/CreateSubjectMissingCategoryDto.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_not_create_subject(String json) throws Exception {
	api.perform(post(path).header("Authorization", userTomas)
	        .contentType(MediaType.APPLICATION_JSON).content(json))
	        .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/subjectcontroller/CreateSubjectDtoFalse.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_user_not_create_subject_bad_inputs(String json)
            throws Exception {
	api.perform(post(path).header("Authorization", userTomas)
	        .contentType(MediaType.APPLICATION_JSON).content(json))
	        .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/subjectcontroller/CreateSubjectDto.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_not_authentified_not_create_subject(String json)
            throws Exception {
	api.perform(post(path).contentType(MediaType.APPLICATION_JSON)
	        .content(json)).andExpect(status().isForbidden());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/subjectcontroller/UpVoteDto.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_update_vote_from_user(String json) throws Exception {
	api.perform(post(path + "/vote/2").header("Authorization", userTomas)
	        .contentType(MediaType.APPLICATION_JSON).content(json))
	        .andExpect(status().isOk())
	        .andExpect(
	                jsonPath("$.title").value("Spring database requests"))
	        .andExpect(jsonPath("$.hasVoted").value(true));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/subjectcontroller/WithdrawVoteDto.csv",
            delimiterString = DELIMITER, numLinesToSkip = 1)
    void should_withdraw_vote_from_user(String json) throws Exception {
	api.perform(post(path + "/vote/1").header("Authorization", userTomas)
	        .contentType(MediaType.APPLICATION_JSON).content(json))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.title").value("Angular 8 Modals"))
	        .andExpect(jsonPath("$.hasVoted").value(false));
    }

    @Test
    void should_user_get_all_not_scheduled_with_vote() throws Exception {
	api.perform(get(path).header("Authorization", userTomas))
	        .andExpect(status().isOk());
    }

    @Test
    void should_admin_get_all_not_scheduled_with_vote() throws Exception {
	api.perform(get(path).header("Authorization", adminJohanna))
	        .andExpect(status().isOk());
    }

    @Test
    void should_not_get_all_not_scheduled_with_vote() throws Exception {
	api.perform(get(path)).andExpect(status().isForbidden());
    }
}
