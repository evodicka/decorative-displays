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
