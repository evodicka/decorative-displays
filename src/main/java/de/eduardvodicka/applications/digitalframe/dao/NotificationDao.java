package de.eduardvodicka.applications.digitalframe.dao;

import de.eduardvodicka.applications.digitalframe.model.Notification;

import java.util.List;

/**
 * Created by evodicka on 02.01.2016.
 */
public interface NotificationDao {

    int getCount();

    List<Notification> findAll();

    Notification find(int id);
}
