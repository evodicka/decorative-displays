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
