package fr.formation.masterpiece.commons.config;

import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class BeanConfig {

    private static final String FALSE = "false";

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

    @Bean
    public JavaMailSender getJavaMailSender() {
	final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	javaMailSender.setHost("smtp.gmail.com");
	javaMailSender.setProtocol("smtp");
	javaMailSender.setPort(587);
	final Properties props = javaMailSender.getJavaMailProperties();
	props.put("mail.smtp.auth", FALSE);
	props.put("mail.smtp.starttls", FALSE);
	props.put("mail.debug", FALSE);
	return javaMailSender;
    }
}
