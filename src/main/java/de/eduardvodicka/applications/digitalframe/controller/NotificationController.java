package de.eduardvodicka.applications.digitalframe.controller;

import de.eduardvodicka.applications.digitalframe.dao.NotificationDao;
import de.eduardvodicka.applications.digitalframe.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by evodicka on 14.11.2015.
 */
@RestController
public class NotificationController {

    @Autowired
    private NotificationDao notificationDao;

    @RequestMapping(value = "/notification/current", method = RequestMethod.GET)
    public Notification getCurrentNotification() {
        int count = notificationDao.getCount();

        if(count > 0) {
            int id = (int) ((Math.random() * 100)) % count;
            return notificationDao.find(id);
        }
        return new Notification();
    }
}
