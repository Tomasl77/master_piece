package fr.formation.masterpiece.domain.dtos;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class SubjectCreateDtoTest extends JUnitConfigTest {

    @Test
    public void should_construct() {
	SubjectCreateDto actual = new SubjectCreateDto();
	assertNotNull(actual);
    }
}
