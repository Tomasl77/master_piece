package fr.formation.masterpiece.api.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import fr.formation.masterpiece.config.IntegrationTestConfig;

class CategoryControllerTest extends IntegrationTestConfig {

    @Value("${mock.path.categories}")
    private String path;

    @Test
    void should_not_get_categories_unidentified_user() throws Exception {
	api.perform(get(path)).andExpect(status().isForbidden());
    }

    @Test
    void should_get_categories_identified_user() throws Exception {
	api.perform(get(path).header("Authorization", userTomas))
	        .andExpect(status().isOk());
    }

    @Test
    void should_get_categories_identified_admin() throws Exception {
	api.perform(get(path).header("Authorization", adminJohanna))
	        .andExpect(status().isOk());
    }
}
