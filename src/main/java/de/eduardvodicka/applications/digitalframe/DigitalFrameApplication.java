package de.eduardvodicka.applications.digitalframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entrypoint of the Spring Boot Application.
 *
 * @author Eduard Vodicka
 */
@SpringBootApplication
@EnableAutoConfiguration
public class DigitalFrameApplication {

    /**
     * Spring Boot initializer
     * @param args default java main arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DigitalFrameApplication.class, args);
    }
}
