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

package de.eduardvodicka.applications.digitalframe.dao.provider.classpath;

import de.eduardvodicka.applications.digitalframe.dao.ImagesDao;
import de.eduardvodicka.applications.digitalframe.model.ImageResource;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Image provider that reads from a directory on the classpath
 *
 * @author Eduard Vodicka
 */
public class ClasspathImageProvider implements ImagesDao {

    private String directoryPath;

    private PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();

    /**
     * Instantiates the provider with a given directory path
     * @param directoryPath path to the images directory
     */
    public ClasspathImageProvider(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    public List<ImageResource> findAll() {
        List<ImageResource> resources = new ArrayList<>();
        getImageNamesStream().forEach(resource -> {
            ImageResource res = new ImageResource();
            res.setName(resource.getFilename());
            resources.add(res);
        });

        return resources;
    }

    @Override
    public InputStream findImage(String imageName) {
        return getClass().getResourceAsStream(getFilePath(imageName));
    }

    @Override
    public void stream(String imageName, OutputStream stream) {
        try {
            InputStream inputStream = findImage(imageName);
            IOUtils.copy(inputStream, stream);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private String getFilePath(String imageName) {
        return directoryPath.split(":")[1] + "/" + imageName + ".jpg";
    }

    private Stream<Resource> getImageNamesStream() {
        try {
            Resource[] resources = pathResolver.getResources(directoryPath + "/*");
            return Arrays.stream(resources).filter(resource -> resource.getFilename().endsWith(".jpg"));

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public long getCount() {
        return getImageNamesStream().count();
    }
}
