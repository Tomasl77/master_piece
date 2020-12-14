package fr.formation.masterpiece.commons.config;

import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Configuration class declaring configuration beans.
 * <p>
 * Provides default configuration for beans such as {@link ObjectMapper},
 * {@link ModelMapper} or {@link PasswordEncoder}. Please note that those beans
 * are configured for the sake of all this application and SHOULD NOT be
 * overridden.
 *
 * @author Tomas LOBGEOIS
 */
@Configuration
public class BeanConfig {

    private String host;

    private int port;

    private String username;

    private String password;

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
	        .setMatchingStrategy(MatchingStrategies.STRICT);
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
    @ConfigurationProperties(prefix = "mail")
    public JavaMailSender getJavaMailSender() {
	final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	javaMailSender.setHost(host);
	javaMailSender.setPort(port);
	javaMailSender.setUsername(username);
	javaMailSender.setPassword(password);
	final Properties props = javaMailSender.getJavaMailProperties();
	props.put("mail.transport.protocol", "smtp");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.debug", "true");
	return javaMailSender;
    }
}
