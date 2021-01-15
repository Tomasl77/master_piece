package fr.formation.masterpiece.domain.dtos.sharingsessions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class SharingSessionDtoTest extends UnitTestConfig {

    @Test
    void should_construct() {
	SharingSessionDto tested = new SharingSessionDto();
	assertNotNull(tested);
    }
}
