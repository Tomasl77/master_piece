package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.formation.masterpiece.domain.dtos.views.SubjectViewDto;
import fr.formation.masterpiece.security.annotations.MockUserForTests;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles(profiles = "dev")
class SubjectControllerTest {

    @Autowired
    private SubjectController subjectController;

    @Test
    @MockUserForTests
    void should_return_all_subject() {
	List<SubjectViewDto> actual = subjectController.getAll();
	assertEquals(14, actual.size());
    }
}
