package de.eduardvodicka.applications.digitalframe.controller;

import de.eduardvodicka.applications.digitalframe.dao.ImagesDao;
import de.eduardvodicka.applications.digitalframe.model.ImageResource;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by evodicka on 23.01.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ImagesControllerTest {

    @Mock
    private ImagesDao imagesDao;

    @Mock
    private ResourceSelector resourceSelector;

    @InjectMocks
    private ImagesController underTest = new ImagesController();

    @Test
    public void currentResource() {
        int selectedid = 1;
        ImageResource resource1 = new ImageResource();
        resource1.setId(0);
        ImageResource resource2 = new ImageResource();
        resource2.setId(1);

        when(resourceSelector.selectResourceId(anyInt())).thenReturn(selectedid);
        when(imagesDao.findAll()).thenReturn(Arrays.asList(resource1, resource2));

        ImageResource result = underTest.getCurrentImageResource();

        assertThat(result, CoreMatchers.is(resource2));
    }

    public void currentResource_noResult() {
        when(imagesDao.findAll()).thenReturn(new ArrayList<>());

        ImageResource result = underTest.getCurrentImageResource();

        assertThat(result, CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    @Test
    public void download() throws IOException {
        String imageName = "name";
        int id = 1;

        try(InputStream stream = new ByteArrayInputStream(new byte[]{1, 2, 3, 4})) {
            when(imagesDao.findImage(anyString())).thenReturn(stream);

            underTest.downloadImage(imageName, id);

            Mockito.verify(imagesDao).findImage(imageName);
        }
    }

}