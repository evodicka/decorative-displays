package de.eduardvodicka.applications.digitalframe.controller;

import de.eduardvodicka.applications.digitalframe.dao.NotificationDao;
import de.eduardvodicka.applications.digitalframe.model.Notification;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by evodicka on 23.01.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class NotificationControllerTest {

    @Mock
    private NotificationDao notificationDao;

    @Mock
    private ResourceSelector resourceSelector;

    @InjectMocks
    private NotificationController underTest = new NotificationController();

    @Test
    public void current() {
        Notification not = new Notification();

        when(resourceSelector.selectResourceId(anyInt())).thenReturn(2);
        when(notificationDao.getCount()).thenReturn(5);
        when(notificationDao.find(anyInt())).thenReturn(not);

        Notification result = underTest.getCurrentNotification();

        verify(notificationDao).find(2);
        assertThat(result, CoreMatchers.is(not));
    }

    @Test
    public void current_noResults() {
        when(notificationDao.getCount()).thenReturn(0);

        Notification result = underTest.getCurrentNotification();

        assertThat(result, CoreMatchers.is(CoreMatchers.notNullValue()));
    }

}