/*
 * Copyright 2015 Eduard Vodicka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
