package fr.formation.masterpiece.commons.config;

import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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
@EnableCaching
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
	ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();
	mapper.findAndRegisterModules();
	mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	mapper.setVisibility(
	        mapper.getSerializationConfig().getDefaultVisibilityChecker()
	                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
	                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
	                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
	                .withSetterVisibility(JsonAutoDetect.Visibility.NONE));
	return mapper;
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
	return javaMailSender;
    }

    @Bean
    public CacheManager cacheManager() {
	return new ConcurrentMapCacheManager("categories");
    }
}
