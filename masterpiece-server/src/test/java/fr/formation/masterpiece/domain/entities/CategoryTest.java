package fr.formation.masterpiece.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class CategoryTest extends UnitTestConfig {

    @Test
    void should_construct() {
	Category actual = new Category();
	assertNotNull(actual);
    }

    @Test
    void should_get_name() {
	Category actual = new Category();
	assertNotNull(actual);
    }

    void should_to_string() {
	Category actual = new Category();
	assertEquals("{name: null}", actual.toString());
    }
}
