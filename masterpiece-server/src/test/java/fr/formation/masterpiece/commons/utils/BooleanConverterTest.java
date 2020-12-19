package fr.formation.masterpiece.commons.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.formation.masterpiece.config.UnitTestConfig;

class BooleanConverterTest extends UnitTestConfig {

    @Test
    void should_convert_true_boolean() {
	BooleanConverter tested = new BooleanConverter();
	String expected = tested.convertToDatabaseColumn(true);
	assertEquals(expected, "T");
    }

    @Test
    void should_convert_false_boolean() {
	BooleanConverter tested = new BooleanConverter();
	String expected = tested.convertToDatabaseColumn(false);
	assertEquals(expected, "F");
    }

    @Test
    void should_convert_T_to_boolean_true() {
	BooleanConverter tested = new BooleanConverter();
	boolean expected = tested.convertToEntityAttribute("T");
	assertTrue(expected);
    }

    @Test
    void should_convert_F_to_boolean_false() {
	BooleanConverter tested = new BooleanConverter();
	boolean expected = tested.convertToEntityAttribute("F");
	assertFalse(expected);
    }
}
