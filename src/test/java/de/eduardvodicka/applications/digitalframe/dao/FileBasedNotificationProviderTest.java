package de.eduardvodicka.applications.digitalframe.dao;

import de.eduardvodicka.applications.digitalframe.dao.provider.FileBasedNotificationProvider;
import de.eduardvodicka.applications.digitalframe.model.Notification;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by evodicka on 02.01.2016.
 */
public class FileBasedNotificationProviderTest {

    private static final String LINE_1 = "Line 1";
    private static final String LINE_2 = "Line 2";

    private FileBasedNotificationProvider underTest = new FileBasedNotificationProvider() {
        @Override
        protected List<String> loadLines() {
            return Arrays.asList(new String[] {LINE_1, LINE_2});
        }
    };

    @Test
    public void findAll() {
        List<Notification> result = underTest.findAll();

        assertThat(result.size(), is(2));
        assertThat(result.get(0).getId(), is(0));
        assertThat(result.get(0).getText(), is(LINE_1));
        assertThat(result.get(1).getId(), is(1));
        assertThat(result.get(1).getText(), is(LINE_2));
    }

    @Test
    public void count() {
        int count = underTest.getCount();

        assertThat(count, is(2));
    }

    @Test
    public void find() {
        Notification result = underTest.find(1);

        assertThat(result, notNullValue());
        assertThat(result.getId(), is(1));
        assertThat(result.getText(), is(LINE_2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void find_idTooHigh() {
        underTest.find(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void find_idTooLow() {
        underTest.find(-1);
    }

}
