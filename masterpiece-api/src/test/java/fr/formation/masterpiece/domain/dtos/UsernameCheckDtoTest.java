package fr.formation.masterpiece.domain.dtos;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UsernameCheckDtoTest extends JUnitConfigTest {

    @Test
    void should_be_valid() {
	UsernameCheckDto tested = new UsernameCheckDto();
	tested.setValid(true);
	assertTrue(tested.isValid());
    }
}
