package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.config.JUnitConfigTest;
import fr.formation.masterpiece.domain.dtos.views.SubjectViewDto;
import fr.formation.masterpiece.security.annotations.MockUserForTests;

class SubjectControllerTest extends JUnitConfigTest {

    @Autowired
    private SubjectController subjectController;

    @Test
    @MockUserForTests
    void should_return_all_subject() {
	List<SubjectViewDto> actual = subjectController.getAll();
	assertEquals(14, actual.size());
    }
}
