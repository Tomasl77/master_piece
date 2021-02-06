package fr.formation.masterpiece.domain.dtos.sharingsessions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SharingSessionViewDtoTest {

    private static SharingSessionViewDto objectToTest;

    private static LocalDateTime now;

    private static LocalDateTime tomorrow;

    @BeforeAll
    static void set_up() {
	now = LocalDateTime.now();
	tomorrow = LocalDateTime.now().plus(1, ChronoUnit.DAYS);
	objectToTest = new SharingSessionViewDto(1L, now, tomorrow, "Unit Test",
	        "Thierry");
    }

    @Test
    void should_construct_not_null() {
	SharingSessionViewDto tested = new SharingSessionViewDto();
	assertNotNull(tested);
    }

    @Test
    void should_construct_with_args_not_null() {
	assertNotNull(objectToTest);
    }

    @Test
    void should_get_start_time() {
	assertEquals(now, objectToTest.getStartTime());
    }

    @Test
    void should_get_end_time() {
	assertEquals(tomorrow, objectToTest.getEndTime());
    }

    @Test
    void should_get_subject() {
	assertEquals("Unit Test", objectToTest.getSubject());
    }

    @Test
    void should_get_lecturer() {
	assertEquals("Thierry", objectToTest.getLecturer());
    }
}
