package fr.formation.masterpiece.api.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    /**
     * This method is here to test not supported exception in
     * {@link fr.formation.masterpiece.commons.exceptions.GlobalControllerException#handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException, HttpHeaders, HttpStatus, WebRequest)
     * Method not supported}
     */
    @Test
    void should_method_not_be_supported() throws Exception {
	api.perform(delete(path).header("Authorization", userTomas))
	        .andExpect(status().isMethodNotAllowed())
	        .andExpect(jsonPath("$.message")
	                .value("Request method 'DELETE' not supported"))
	        .andExpect(jsonPath("$.errors.[0]").value(
	                "DELETE method is not supported for this request. Supported methods are GET "));
    }

    /**
     * This method is here to test no handler found in
     * {@link fr.formation.masterpiece.commons.exceptions.GlobalControllerException#handleNoHandlerFoundException(NoHandlerFoundException , HttpHeaders, HttpStatus, WebRequest)
     * No handler found}
     */
    @Test
    void should_not_found_handler() throws Exception {
	api.perform(get(path + "/nothing").header("Authorization", userTomas))
	        .andExpect(status().isNotFound());
    }
}
