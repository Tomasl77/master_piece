package fr.formation.masterpiece.domain.dtos.users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class CustomUserViewDtoTest extends UnitTestConfig {

    @Test
    void should_to_string() {
	CustomUserViewDto actual = new CustomUserViewDto(2L, "Frank",
	        "frank@gmail.com");
	assertEquals("{id: 2, username: Frank, email: frank@gmail.com}",
	        actual.toString());
    }
}
