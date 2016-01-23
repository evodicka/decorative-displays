package de.eduardvodicka.applications.digitalframe.dao;

import de.eduardvodicka.applications.digitalframe.model.ImageResource;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Data access object for image resources.
 *
 * @author Eduard Vodicka
 */
public interface ImagesDao {

    /**
     * Retrieves all images that are accesible through this dao. Currently the images are limited to the file
     * ending .jpg
     * @return List of all available images
     */
    List<ImageResource> findAll();

    /**
     * Returns a stream with the actual content of an image
     * @param imageName the name of the image without file ending
     * @return InputStream
     */
    InputStream findImage(String imageName);

    /**
     * Writes the actual content of an image into the provided stream.
     * @param imageName the name of the image without file ending
     * @param stream stream to write the data to
     */
    void stream(String imageName, OutputStream stream);

    /**
     * Provides the number of available images
     * @return the number
     */
    long getCount();

}
