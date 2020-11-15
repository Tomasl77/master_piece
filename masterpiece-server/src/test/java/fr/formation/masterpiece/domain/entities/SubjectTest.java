package fr.formation.masterpiece.domain.entities;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class SubjectTest extends JUnitConfigTest {

    @Test
    public void should_construct() {
	Subject actual = new Subject();
	assertNotNull(actual);
    }
}
