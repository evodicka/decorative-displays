package de.eduardvodicka.applications.digitalframe.controller;

import de.eduardvodicka.applications.digitalframe.dao.NotificationDao;
import de.eduardvodicka.applications.digitalframe.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring MVC controller to access text based notifications. Registers its methods under the path /notification/*
 *
 * @author Eduard Vodicka
 */
@RestController
public class NotificationController {

    @Autowired
    private NotificationDao notificationDao;

    @Autowired
    @Qualifier("randomSelector")
    private ResourceSelector resourceSelector;

    /**
     * Retrieves the current notification based on a random algorithm.
     *
     * @see RandomResourceSelector
     * @return the notification
     */
    @RequestMapping(value = "/notification/current", method = RequestMethod.GET)
    public Notification getCurrentNotification() {
        int count = notificationDao.getCount();

        if(count > 0) {
            int id = resourceSelector.selectResourceId(count);
            return notificationDao.find(id);
        }
        return new Notification();
    }
}
