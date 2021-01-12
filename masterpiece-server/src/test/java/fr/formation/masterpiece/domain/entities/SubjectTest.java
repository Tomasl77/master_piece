package fr.formation.masterpiece.domain.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class SubjectTest extends UnitTestConfig {

    @Test
    public void should_construct() {
	Subject actual = new Subject();
	assertNotNull(actual);
    }

    @Test
    void should_to_string() {
	Subject actual = new Subject();
	assertEquals(
	        "{title: null, description: null, category: null, requester: null}",
	        actual.toString());
    }
}
