package de.eduardvodicka.applications.digitalframe.dao;

import de.eduardvodicka.applications.digitalframe.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by evodicka on 05.12.2015.
 */
@Service
public class NotificationDao {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationDao.class);

    private List<Notification> currentNotifications;

    @Value("${text.filepath}")
    private String filePath;

    @PostConstruct
    public void init() {
        currentNotifications = new ArrayList<>();
        try {
            List<String> strings = loadLines();
            for(int i = 0; i < strings.size(); i++) {
                Notification notification = new Notification();
                notification.setId(i);
                notification.setText(strings.get(i));
                currentNotifications.add(notification);
            }

        } catch (IOException e) {
            LOG.error("Could not read notifications from file", e);
        }
    }

    private List<String> loadLines() throws IOException {
        if(filePath.startsWith("classpath:")) {
            String resourceName = filePath.split(":")[1];
            try (InputStream resource = getClass().getResourceAsStream(resourceName)) {
                return new BufferedReader(new InputStreamReader(resource,
                        StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
            }
        }
        return Files.readAllLines(new File(filePath).toPath());
    }

    public List<Notification> findAll() {
        return new ArrayList<>(currentNotifications);
    }

    public int getCount() {
        return currentNotifications.size();
    }

    public Notification find(int id) {
        if(id < 0 && id >= currentNotifications.size()) {
            throw new IllegalArgumentException("ID " + id + " does not exist");
        }
        return currentNotifications.get(id);
    }

    public void persist(Notification notification) {
        notification.setId(currentNotifications.size() -1);
        currentNotifications.add(notification);
    }

}
