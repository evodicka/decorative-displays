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

package de.eduardvodicka.applications.digitalframe.dao.provider.classpath;

import de.eduardvodicka.applications.digitalframe.dao.provider.FileBasedNotificationProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Notification provider that reads from a file on the classpath
 *
 * @author Eduard Vodicka
 */
public class ClasspathNotificationProvider extends FileBasedNotificationProvider {

    private String filePath;

    /**
     * Instantiates the provider with a given file path
     * @param filePath path to the notification file
     */
    public ClasspathNotificationProvider(String filePath) {
        this.filePath = filePath;
    }

    @Override
    protected List<String> loadLines() {
        try (BufferedReader buffered = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath), StandardCharsets.UTF_8))) {
            return buffered.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
