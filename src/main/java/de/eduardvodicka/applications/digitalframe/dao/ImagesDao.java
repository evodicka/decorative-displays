package de.eduardvodicka.applications.digitalframe.dao;

import de.eduardvodicka.applications.digitalframe.model.ImageResource;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by evodicka on 02.01.2016.
 */
public interface ImagesDao {

    List<ImageResource> findAll();

    InputStream findImage(String imageName);

    void stream(String imageName, OutputStream stream);

    long getCount();

}
