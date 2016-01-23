package de.eduardvodicka.applications.digitalframe.model;

/**
 * Representation of an Images that will serve as background image of the UI screen
 *
 * @author Eduard Vodicka
 */
public class ImageResource {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
