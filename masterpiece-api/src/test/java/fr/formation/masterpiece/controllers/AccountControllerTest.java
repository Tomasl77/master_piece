package fr.formation.masterpiece.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.masterpiece.domain.dtos.AccountRegisterDto;
import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.domain.entities.Role;
import fr.formation.masterpiece.domain.entities.User;
import fr.formation.masterpiece.services.AccountService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
@AutoConfigureWebMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    AccountController accountController;

    @MockBean
    AccountService mockService;

    @BeforeEach
    void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);
	Set<Role> setRole = new HashSet<>();
	setRole.add(new Role("toto", true));
	User userDto = new User();
	userDto.setUsername("Tomas");
	userDto.setPassword("toto");
	userDto.setRole(setRole);
    }

    @Test
    void should_check_username() {
	// Given
	// When
	when(mockService.checkUsername("Tomas"))
	        .thenReturn(new UsernameCheckDto(true));
	UsernameCheckDto actual = mockService.checkUsername("Tomas");
	// Then
	assertTrue(actual.isValid());
    }

    @Test
    void should_create_user() throws Exception {
	AccountRegisterDto dto = new AccountRegisterDto();
	dto.setUsername("toto");
	Set<Role> role = new HashSet<>();
	role.add(new Role("toto", true));
	User user = new User();
	user.setUsername("Johanna");
	user.setPassword("Johannaely7@");
	user.setRole(role);
	// Given
	// When
	doAnswer(new AnswerUser()).when(mockService).create(any());
	mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
	        .content(asJsonString(user))
	        .contentType(MediaType.APPLICATION_JSON)
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isCreated())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    // Then

    private class AnswerUser implements Answer {

	@Override
	public Object answer(InvocationOnMock invocation) throws Throwable {
	    String[] arg = (String[]) invocation.getArguments();
	    return arg.toString();
	}
    }

    private String asJsonString(final Object obj) {
	try {
	    return new ObjectMapper().writeValueAsString(obj);
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }
}
