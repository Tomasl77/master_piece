package fr.formation.masterpiece.domain.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class SubjectCreateDtoTest extends JUnitConfigTest {

    @Test
    void should_set_category() {
	SubjectCreateDto tested = new SubjectCreateDto();
	tested.setCategory("BACK");
	assertEquals("BACK", tested.getCategory());
    }

    @Test
    void should_set_title() {
	SubjectCreateDto tested = new SubjectCreateDto();
	tested.setTitle("Spring Test");
	assertEquals("Spring Test", tested.getTitle());
    }

    @Test
    void should_set_description() {
	SubjectCreateDto tested = new SubjectCreateDto();
	tested.setDescription(
	        "I want to have some clues about spring boot test");
	assertEquals("I want to have some clues about spring boot test",
	        tested.getDescription());
    }
}
