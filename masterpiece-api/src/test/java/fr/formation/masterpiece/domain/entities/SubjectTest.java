package fr.formation.masterpiece.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.JUnitConfigTest;

class SubjectTest extends JUnitConfigTest {

    @Test
    void should_set_title() {
	Subject tested = new Subject();
	tested.setTitle("Spring Test");
	assertEquals("Spring Test", tested.getTitle());
    }

    @Test
    void should_set_description() {
	Subject tested = new Subject();
	tested.setDescription("Angular 8 component");
	assertEquals("Angular 8 component", tested.getDescription());
    }

    @Test
    void should_set_category() {
	Subject tested = new Subject();
	tested.setCategory("FRONTEND");
	assertEquals("FRONTEND", tested.getCategory());
    }

    @Test
    void should_set_vote() {
	Subject tested = new Subject();
	tested.setVote(3);
	assertEquals(3, tested.getVote());
    }
}
