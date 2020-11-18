package fr.formation.masterpiece.commons.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BeanConfig {

    /**
     * Default {@code ModelMapper} bean that configures mapping between DTO and
     * entities.
     * <p>
     * This builder prevents XXE injection
     */
    @Bean
    protected ObjectMapper objectMapper() {
	return Jackson2ObjectMapperBuilder.json().build();
    }

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
}
