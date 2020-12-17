package fr.formation.masterpiece.domain.dtos;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserEmailCheckDtoTest extends JUnitConfigTest {

    @Test
    void should_construct() {
	UserEmailCheckDto actual = new UserEmailCheckDto();
	assertNotNull(actual);
    }
}
