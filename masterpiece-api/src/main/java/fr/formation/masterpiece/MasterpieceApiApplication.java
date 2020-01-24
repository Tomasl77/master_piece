package fr.formation.masterpiece;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableSwagger2
public class MasterpieceApiApplication {

    public static void main(String[] args) {
	SpringApplication.run(MasterpieceApiApplication.class, args);
    }

    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2).select()
	        .apis(RequestHandlerSelectors
	                .basePackage("fr.formation.masterpiece"))
	        .paths(PathSelectors.any()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }
}
