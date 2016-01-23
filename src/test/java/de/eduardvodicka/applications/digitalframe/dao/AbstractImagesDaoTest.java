package de.eduardvodicka.applications.digitalframe.dao;

import de.eduardvodicka.applications.digitalframe.model.ImageResource;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by evodicka on 23.01.2016.
 */
public abstract class AbstractImagesDaoTest {

    private static final String IMAGE_1 = "dog_test";
    private static final String IMAGE_2 = "eagle_test";

    private ImagesDao underTest;

    @Test
    public void findAll() {
        List<ImageResource> result = underTest.findAll();

        assertThat(result, is(notNullValue()));
        assertThat(result.size(), is(2));
        assertThat(result.get(0).getName(), is(IMAGE_1 + ".jpg"));
        assertThat(result.get(1).getName(), is(IMAGE_2 + ".jpg"));
    }

    @Test
    public void findFile() throws IOException {
        InputStream result = underTest.findImage(IMAGE_1);

        assertThat(result, is(notNullValue()));
        assertThat(result.available(), greaterThan(0));
    }

    @Test
    public void count() {
        long result = underTest.getCount();

        assertThat(result, is(2L));
    }

    @Test
    public void stream() throws IOException {
        try (OutputStream stream = new ByteArrayOutputStream()) {
            underTest.stream(IMAGE_2, stream);
        }
    }

    protected void setUnderTest(ImagesDao underTest) {
        this.underTest = underTest;
    }
}