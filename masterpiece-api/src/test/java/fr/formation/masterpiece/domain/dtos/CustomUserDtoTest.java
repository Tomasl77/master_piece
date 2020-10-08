package fr.formation.masterpiece.domain.dtos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class CustomUserDtoTest extends JUnitConfigTest {

    @Test
    void should_construct() {
	CustomUserDto tested = new CustomUserDto();
	assertNotNull(tested);
    }
}
