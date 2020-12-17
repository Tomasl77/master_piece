package fr.formation.masterpiece.domain.entities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class CategoryTest extends JUnitConfigTest {

    @Test
    void should_construct() {
	Category actual = new Category();
	assertNotNull(actual);
    }

    void should_get_name() {
	Category actual = new Category();
	assertNotNull(actual);
    }
}