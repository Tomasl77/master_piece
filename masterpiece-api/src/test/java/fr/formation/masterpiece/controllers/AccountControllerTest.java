package fr.formation.masterpiece.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.formation.masterpiece.domain.dtos.UsernameCheckDto;
import fr.formation.masterpiece.services.AccountService;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountService mockService;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testCheckUsername() {
	// Given
	// When
	when(mockService.checkUsername("Tomas"))
	        .thenReturn(new UsernameCheckDto(true));
	UsernameCheckDto actual = mockService.checkUsername("Tomas");
	// Then
	assertTrue(actual.isValid());
    }

    @Test
    void testCreate() {
	// Given
	// When
	// when(mockService.create());
	// Then
    }
}
