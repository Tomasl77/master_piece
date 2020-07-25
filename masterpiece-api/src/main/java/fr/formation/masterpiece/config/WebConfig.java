package fr.formation.masterpiece.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Defines the "/api" prefix for all {@code @RestController} in the
     * application (except the one for /oauth/token, since it's not a
     * {@link RestController}.
     *
     * @param configurer a path configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
	configurer.addPathPrefix("/api",
	        HandlerTypePredicate.forAnnotation(RestController.class));
    }
}
