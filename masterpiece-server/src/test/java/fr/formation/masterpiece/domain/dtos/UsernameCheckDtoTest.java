package fr.formation.masterpiece.domain.dtos;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class UsernameCheckDtoTest extends UnitTestConfig {

    @Test
    void should_construct_false() {
	UsernameCheckDto tested = new UsernameCheckDto(false);
	assertNotNull(tested);
    }
}
