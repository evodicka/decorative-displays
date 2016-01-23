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

package de.eduardvodicka.applications.digitalframe.controller;

import de.eduardvodicka.applications.digitalframe.dao.ImagesDao;
import de.eduardvodicka.applications.digitalframe.model.ImageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;

/**
 * Spring MVC controller that handles images loading. Registers its methods to the path /images/*
 *
 * @author Eduard Vodicka
 */
@RestController
public class ImagesController {

    @Autowired
    private ImagesDao imagesDao;

    @Autowired
    @Qualifier("timeBasedSelector")
    private ResourceSelector resourceSelector;

    /**
     * Loads the current image resource metadata based on the algorithm provided by the {@link ResourceSelector}, which
     * is time based by default
     *
     * @see TimeBasedResourceSelector
     * @return the image resource
     */
    @RequestMapping(value = "/images/current", method = RequestMethod.GET)
    public ImageResource getCurrentImageResource() {
        List<ImageResource> resources = imagesDao.findAll();

        if(resources.size() > 0) {
            return resources.get(resourceSelector.selectResourceId(resources.size()));
        }
        return new ImageResource();
    }

    /**
     * Offers the contents of an image identified by its name and id for download
     * @param imageName name of the image
     * @param id id of the image
     * @return download stream
     */
    @RequestMapping(value = "/images/{id}/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadImage(@PathVariable("name") String imageName, @PathVariable("id") int id) {
        InputStream stream = imagesDao.findImage(imageName);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(stream));
    }
}
