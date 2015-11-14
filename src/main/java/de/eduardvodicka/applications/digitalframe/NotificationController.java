package de.eduardvodicka.applications.digitalframe;

import de.eduardvodicka.applications.digitalframe.model.Notification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by evodicka on 14.11.2015.
 */
@RestController
public class NotificationController {

    @RequestMapping(value = "/notification/current", method = RequestMethod.GET)
    public Notification getCurrentNotification() {
        Notification notification = new Notification();
        notification.setId(new Random().nextInt());
        notification.setText("TEST");

        return notification;
    }

}
