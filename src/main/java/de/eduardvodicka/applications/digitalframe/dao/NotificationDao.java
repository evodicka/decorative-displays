package de.eduardvodicka.applications.digitalframe.dao;

import de.eduardvodicka.applications.digitalframe.dao.provider.NotificationProvider;
import de.eduardvodicka.applications.digitalframe.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by evodicka on 05.12.2015.
 */
@Service
public class NotificationDao {

    private static final Logger LOG = LoggerFactory.getLogger(NotificationDao.class);

    private List<Notification> currentNotifications;

    @Autowired
    private NotificationProvider provider;

    @PostConstruct
    public void init() {
        currentNotifications = new ArrayList<>();
        try {
            List<String> strings = provider.loadLines();
            for(int i = 0; i < strings.size(); i++) {
                Notification notification = new Notification();
                notification.setId(i);
                notification.setText(strings.get(i));
                currentNotifications.add(notification);
            }

        } catch (Exception e) {
            LOG.error("Could not read notifications from file", e);
        }
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
