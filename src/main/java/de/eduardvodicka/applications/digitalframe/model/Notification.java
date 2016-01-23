package de.eduardvodicka.applications.digitalframe.model;

/**
 * Represntation of a Notification that should be displayed on the UI Screen
 *
 * @author Eduard Vodicka
 */
public class Notification {

    private int id;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
