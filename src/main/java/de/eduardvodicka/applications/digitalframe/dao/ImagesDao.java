package de.eduardvodicka.applications.digitalframe.dao;

import de.eduardvodicka.applications.digitalframe.model.ImageResource;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by evodicka on 05.12.2015.
 */
@Service
public class ImagesDao {

    @Value("${images.directory}")
    private String directoryPath;

    public long getCount() throws IOException {
        return getImageNamesStream().count();
    }

    public List<ImageResource> findAll() {
        List<ImageResource> resources = new ArrayList<>();
        getImageNamesStream().sorted().forEach(path -> {
            ImageResource resource = new ImageResource();
            resource.setName(path.getFileName().toString());
            resources.add(resource);
        });

        return resources;
    }

    public InputStream findImage(String imageName) {
        try {
            return Files.newInputStream(new File(directoryPath + "/" + imageName + ".jpg").toPath());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void stream(String imageName, OutputStream stream) {
        try {
            InputStream fileStream = Files.newInputStream(new File(directoryPath + "/" + imageName).toPath());
            IOUtils.copy(fileStream, stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Stream<Path> getImageNamesStream() {
        try {
            return Files.walk(new File(directoryPath).toPath())
                    .filter(path -> path.getFileName().toString().endsWith(".jpg"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
