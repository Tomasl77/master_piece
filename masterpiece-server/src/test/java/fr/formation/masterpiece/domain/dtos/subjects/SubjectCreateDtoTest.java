package fr.formation.masterpiece.domain.dtos.subjects;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class SubjectCreateDtoTest extends UnitTestConfig {

    @Test
    public void should_construct() {
	SubjectCreateDto actual = new SubjectCreateDto();
	assertNotNull(actual);
    }
}
