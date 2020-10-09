package fr.formation.masterpiece.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class BeanConfig {

    /**
     * Default {@code ModelMapper} bean that configures mapping between DTO and
     * entities.
     * <p>
     * field matching is enabled with private access and standard matching
     * strategy.
     *
     * @return an instance of {@code ModelMapper}
     */
    @Bean
    protected ModelMapper modelMapper() {
	ModelMapper mapper = new ModelMapper();
	mapper.getConfiguration().setFieldMatchingEnabled(true)
	        .setFieldAccessLevel(AccessLevel.PRIVATE)
	        .setMatchingStrategy(MatchingStrategies.STANDARD);
	return mapper;
    }

    /**
     * The password encoder bean for the application. Used for client and users.
     *
     * @return a password encoder
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    /**
     * Default {@code ObjectMapper} bean that configures mapping between JSON
     * and DTO.
     * <p>
     * Set mapping to field with any visibility and deactivates mapping with
     * accessors/mutators. Configures dates serialization using
     * {@code JavaTimeModule}.
     *
     * @return an instance of {@code ObjectMapper}.
     */
    @Bean
    protected ObjectMapper objectMapper() {
	ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
	mapper.setVisibility(
	        mapper.getSerializationConfig().getDefaultVisibilityChecker()
	                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
	                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
	                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
	                .withSetterVisibility(JsonAutoDetect.Visibility.NONE));
	mapper.registerModule(new JavaTimeModule());
	mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	return mapper;
    }
}
