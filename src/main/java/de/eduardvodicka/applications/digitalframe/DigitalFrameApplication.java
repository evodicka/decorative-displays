package de.eduardvodicka.applications.digitalframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DigitalFrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalFrameApplication.class, args);
    }
}
