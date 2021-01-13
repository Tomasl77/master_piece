package fr.formation.masterpiece.api.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import fr.formation.masterpiece.config.IntegrationTestConfig;

class CacheControllerTest extends IntegrationTestConfig {

    @Value("${mock.path.caches}")
    private String path;

    @Test
    void should_user_not_clear_cache() throws Exception {
	api.perform(
	        get(path + "/categories").header("Authorization", userTomas))
	        .andExpect(status().isForbidden());
    }

    @Test
    void should_admin_clear_cache() throws Exception {
	api.perform(
	        get(path + "/categories").header("Authorization", adminJohanna))
	        .andExpect(status().isOk());
    }
}
