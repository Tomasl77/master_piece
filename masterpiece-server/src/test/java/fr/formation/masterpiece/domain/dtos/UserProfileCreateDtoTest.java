package fr.formation.masterpiece.domain.dtos;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserProfileCreateDtoTest extends JUnitConfigTest {

    @Test
    void should_construct() {
	UserProfileCreateDto actual = new UserProfileCreateDto();
	assertNotNull(actual);
    }
}
