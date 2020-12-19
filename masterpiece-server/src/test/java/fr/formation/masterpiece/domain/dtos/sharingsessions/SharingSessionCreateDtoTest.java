package fr.formation.masterpiece.domain.dtos.sharingsessions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class SharingSessionCreateDtoTest extends UnitTestConfig {

    @Test
    void should_construct() {
	SharingSessionCreateDto tested = new SharingSessionCreateDto();
	assertNotNull(tested);
    }
}
