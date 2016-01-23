package de.eduardvodicka.applications.digitalframe.dao.provider;

import de.eduardvodicka.applications.digitalframe.dao.NotificationDao;
import de.eduardvodicka.applications.digitalframe.model.Notification;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for {@link Notification} providers that are based on reading notifications from a line based text file.
 *
 * @author Eduard Vodicka
 */
public abstract class FileBasedNotificationProvider implements NotificationDao {

    private Notification map(String name, int id) {
        Notification notification = new Notification();
        notification.setId(id);
        notification.setText(name);

        return notification;
    }

    private List<Notification> map(List<String> strings) {
        List<Notification> notifications = new ArrayList<>();
        for(int i = 0; i < strings.size(); i++) {
            Notification notification = map(strings.get(i), i);
            notifications.add(notification);
        }
        return notifications;
    }

    @Override
    public List<Notification> findAll() {
        return new ArrayList<>(map(loadLines()));
    }

    @Override
    public int getCount() {
        return loadLines().size();
    }

    @Override
    public Notification find(int id) {
        List<String> strings = loadLines();
        if(id < 0 || id >= strings.size()) {
            throw new IllegalArgumentException("ID " + id + " does not exist");
        }
        return map(strings.get(id), id);
    }

    /**
     * Loads the actual lines from a text file
     * @return all lines
     */
    protected abstract List<String> loadLines();

}
