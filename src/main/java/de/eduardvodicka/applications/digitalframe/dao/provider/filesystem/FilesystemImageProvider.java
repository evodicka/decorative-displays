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

    /**
     * Instatiates the provider with a given directory path
     * @param directoryPath path to the images directory
     */
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
