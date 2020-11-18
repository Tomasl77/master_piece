package fr.formation.masterpiece.controllers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.formation.masterpiece.api.controllers.SubjectController;
import fr.formation.masterpiece.config.JUnitConfigTest;
import fr.formation.masterpiece.domain.dtos.views.SubjectViewDto;
import fr.formation.masterpiece.security.annotations.MockUserForTests;

class SubjectControllerTest extends JUnitConfigTest {

    @Autowired
    private SubjectController subjectController;
    /*
     * private final String json = "{\"category\": \"BACKEND\"," +
     * "	\"description\": \"demo sur sql\"," +
     * "	\"title\": \"Test create subject\"" + "}";
     */

    @Test
    @MockUserForTests
    void should_return_all_subject() {
	List<SubjectViewDto> actual = subjectController.getAllNotScheduled();
	assertEquals(12, actual.size());
    }
    /*
     * @Test
     *
     * @MockUserForTests void should_create_subject() { SubjectCreateDto dto =
     * jsonConvert(json, SubjectCreateDto.class); SubjectDto tested =
     * subjectController.postSubject(dto); assertEquals("Test create subject",
     * tested.getTitle()); }
     */
}
