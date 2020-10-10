package fr.formation.masterpiece;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

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
}
