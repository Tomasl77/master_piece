package fr.formation.masterpiece.domain.dtos;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class UsernameCheckDtoTest extends JUnitConfigTest {

    @Test
    void sould_construct_false() {
	UsernameCheckDto tested = new UsernameCheckDto(false);
	assertNotNull(tested);
    }
}
