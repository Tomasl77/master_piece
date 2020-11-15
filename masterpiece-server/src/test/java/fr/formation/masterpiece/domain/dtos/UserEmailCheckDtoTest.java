package fr.formation.masterpiece.domain.dtos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UserEmailCheckDtoTest extends JUnitConfigTest {

    @Test
    void should_be_valid() {
	UserEmailCheckDto tested = new UserEmailCheckDto();
	tested.setValid(true);
	assertTrue(tested.isValid());
    }

    @Test
    void should_construct() {
	UserEmailCheckDto actual = new UserEmailCheckDto();
	assertNotNull(actual);
    }
}
