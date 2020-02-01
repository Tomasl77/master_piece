package fr.formation.masterpiece;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class MasterpieceApiApplication {

    public static void main(String[] args) {
	SpringApplication.run(MasterpieceApiApplication.class, args);
    }
}
