package fr.formation.masterpiece;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@Configuration
@ConfigurationProperties
@PropertySource("classpath:config.properties")
public class MasterpieceApiApplication {

    @Value("${server.url}")
    private String url;

    public static void main(String[] args) {
	SpringApplication.run(MasterpieceApiApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
	final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	final CorsConfiguration corsConfiguration = new CorsConfiguration();
	corsConfiguration.setAllowCredentials(true);
	corsConfiguration.addAllowedOrigin(url);
	corsConfiguration.addAllowedHeader("*");
	corsConfiguration.addAllowedMethod("*");
	urlBasedCorsConfigurationSource.registerCorsConfiguration("/**",
	        corsConfiguration);
	return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    protected ModelMapper modelMapper() {
	ModelMapper mapper = new ModelMapper();
	mapper.getConfiguration().setFieldMatchingEnabled(true)
	        .setFieldAccessLevel(AccessLevel.PRIVATE)
	        .setMatchingStrategy(MatchingStrategies.STANDARD);
	return mapper;
    }
}
