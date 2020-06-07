package fr.formation.masterpiece.services.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import fr.formation.masterpiece.domain.dtos.AccountRegisterDto;
import fr.formation.masterpiece.domain.entities.Role;
import fr.formation.masterpiece.repositories.AccountRepository;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    AccountServiceImpl service;

    @Mock
    AccountRepository repository;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_return_valid() {
	when(!repository.existsByUsername(anyString())).thenReturn(true);
	assertTrue(repository.existsByUsername("tomas"));
    }

    @Test
    void should_create_user() throws Exception {
	// Given
	Set<Role> setRole = new HashSet<>();
	setRole.add(new Role("toto", true));
	AccountRegisterDto userDto = new AccountRegisterDto();
	userDto.setUsername("Tomas");
	userDto.setPassword("toto");
	// When
	when(repository.save(any())).thenReturn(userDto);
	// then
	mockMvc.perform(post("/accounts").content("{json}")
	        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
	        .andExpect(status().isCreated());
    }
}
