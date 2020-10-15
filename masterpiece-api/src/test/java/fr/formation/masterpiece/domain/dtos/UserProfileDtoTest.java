package fr.formation.masterpiece.domain.dtos;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserProfileDtoTest extends JUnitConfigTest {

    @Test
    void should_construct() {
	UserProfileDto tested = new UserProfileDto();
	assertNotNull(tested);
    }
}
