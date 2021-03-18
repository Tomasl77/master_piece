package fr.formation.masterpiece.config;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "test")
public class UnitTestConfig {

    @Autowired
    protected ModelMapper modelMapper;

    protected static final ObjectMapper MAPPER = Jackson2ObjectMapperBuilder
            .json().build();

    @BeforeAll
    protected static void setUp() {
	MAPPER.setVisibility(
	        MAPPER.getSerializationConfig().getDefaultVisibilityChecker()
	                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
	                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
	                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
	                .withSetterVisibility(JsonAutoDetect.Visibility.NONE));
    }

    protected final <D> D jsonConvert(String inputs, Class<D> destinationType) {
	D converted = null;
	try {
	    converted = MAPPER.readValue(inputs, destinationType);
	} catch (IOException ex) {
	    throw new IllegalArgumentException("wrong json string", ex);
	}
	return converted;
    }
}
