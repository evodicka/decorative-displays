package de.eduardvodicka.applications.digitalframe.dao.provider.classpath;

import de.eduardvodicka.applications.digitalframe.dao.provider.FileBasedNotificationProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by evodicka on 02.01.2016.
 */
public class ClasspathNotificationProvider extends FileBasedNotificationProvider {

    private String filePath;

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
