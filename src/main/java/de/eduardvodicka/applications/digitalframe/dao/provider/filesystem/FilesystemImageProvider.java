package de.eduardvodicka.applications.digitalframe.dao.provider.filesystem;

import de.eduardvodicka.applications.digitalframe.dao.ImagesDao;
import de.eduardvodicka.applications.digitalframe.model.ImageResource;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Image provider that reads from a directory on the filesystem
 *
 * @author Eduard Vodicka
 */
public class FilesystemImageProvider implements ImagesDao {

    private String directoryPath;

    public FilesystemImageProvider(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public long getCount() {
        return getImageNamesStream().count();
    }

    @Override
    public List<ImageResource> findAll() {
        List<ImageResource> resources = new ArrayList<>();
        getImageNamesStream().sorted().forEach(path -> {
            ImageResource resource = new ImageResource();
            resource.setName(path.getFileName().toString());
            resources.add(resource);
        });

        return resources;
    }

    @Override
    public InputStream findImage(String imageName) {
        try {
            return getFileInputStream(imageName);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void stream(String imageName, OutputStream stream) {
        try {
            InputStream fileStream = getFileInputStream(imageName);
            IOUtils.copy(fileStream, stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private InputStream getFileInputStream(String imageName) throws IOException {
        return Files.newInputStream(new File(directoryPath + "/" + imageName + ".jpg").toPath());
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
