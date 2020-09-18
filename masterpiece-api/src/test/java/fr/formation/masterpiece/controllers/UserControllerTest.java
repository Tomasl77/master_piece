package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.formation.masterpiece.domain.dtos.views.CustomUserViewDto;
import fr.formation.masterpiece.security.annotations.MockAdminForTests;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles(profiles = "dev")
class UserControllerTest {

    @Autowired
    private UserController memberController;

    @Test
    @MockAdminForTests
    void should_return_all_member() {
	List<CustomUserViewDto> actual = memberController.getAll();
	assertEquals(3, actual.size());
    }
}
