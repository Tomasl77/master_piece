package fr.formation.masterpiece.domain.dtos.users;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class EntityUserCreateDtoTest extends UnitTestConfig {

    @Test
    void should_construct() {
	EntityUserCreateDto actual = new EntityUserCreateDto();
	assertNotNull(actual);
    }

    @Test
    void should_have_email_null() {
	EntityUserCreateDto tested = new EntityUserCreateDto();
	assertNull(tested.getEmail());
    }

    @Test
    void should_have_password_null() {
	EntityUserCreateDto tested = new EntityUserCreateDto();
	assertNull(tested.getPassword());
    }

    @Test
    void should_have_username_null() {
	EntityUserCreateDto tested = new EntityUserCreateDto();
	assertNull(tested.getUsername());
    }
}
