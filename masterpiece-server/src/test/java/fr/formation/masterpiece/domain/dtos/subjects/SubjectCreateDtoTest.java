package fr.formation.masterpiece.domain.dtos.subjects;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;
import fr.formation.masterpiece.domain.dtos.subjects.SubjectCreateDto;

class SubjectCreateDtoTest extends JUnitConfigTest {

    @Test
    public void should_construct() {
	SubjectCreateDto actual = new SubjectCreateDto();
	assertNotNull(actual);
    }
}
