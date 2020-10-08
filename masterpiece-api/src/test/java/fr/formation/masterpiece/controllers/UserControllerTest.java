package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.config.JUnitConfigTest;
import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;
import fr.formation.masterpiece.security.annotations.MockAdminForTests;

class UserControllerTest extends JUnitConfigTest {

    @Autowired
    private UserController userController;

    @Test
    @MockAdminForTests
    void should_return_all_member() {
	List<CustomUserViewDto> actual = userController.getAll();
	assertEquals(4, actual.size());
    }
}
