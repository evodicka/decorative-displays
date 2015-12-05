package de.eduardvodicka.applications.digitalframe.controller;

import de.eduardvodicka.applications.digitalframe.dao.ImagesDao;
import de.eduardvodicka.applications.digitalframe.model.ImageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by evodicka on 05.12.2015.
 */
@RestController
public class ImagesController {

    @Autowired
    private ImagesDao imagesDao;

    @RequestMapping(value = "/images/current", method = RequestMethod.GET)
    public ImageResource getCurrentImageResource() {
        List<ImageResource> resources = imagesDao.findAll();

        int duration = 60 / resources.size();
        int minute = LocalTime.now().getMinute();
        int id = minute / duration;

        return resources.get(id);
    }
}
