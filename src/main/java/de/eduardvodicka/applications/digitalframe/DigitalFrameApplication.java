package de.eduardvodicka.applications.digitalframe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@EnableAutoConfiguration
public class DigitalFrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalFrameApplication.class, args);
    }
}
