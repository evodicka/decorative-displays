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

package de.eduardvodicka.applications.digitalframe.dao;

import de.eduardvodicka.applications.digitalframe.model.Notification;

import java.util.List;

/**
 * Access object for text based notifications
 */
public interface NotificationDao {

    /**
     * Provides the number of available notifications
     * @return
     */
    int getCount();

    /**
     * Retrieves all available notifications
     * @return list of all notifications
     */
    List<Notification> findAll();

    /**
     * Finds a specific notifications based on its id
     * @param id id of the desired notification
     * @return the notification
     */
    Notification find(int id);
}
