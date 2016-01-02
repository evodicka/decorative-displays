package de.eduardvodicka.applications.digitalframe.dao.provider.factory;

import de.eduardvodicka.applications.digitalframe.dao.ImagesDao;
import de.eduardvodicka.applications.digitalframe.dao.NotificationDao;
import de.eduardvodicka.applications.digitalframe.dao.provider.classpath.ClasspathImageProvider;
import de.eduardvodicka.applications.digitalframe.dao.provider.classpath.ClasspathNotificationProvider;
import de.eduardvodicka.applications.digitalframe.test.ClasspathConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

/**
 * Created by evodicka on 02.01.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClasspathConfiguration.class)
public class NotificationProviderFactoryClasspathTest {

    @Autowired
    private NotificationProviderFactory underTest;

    @Test
    public void factory_classpath() throws Exception {
        NotificationDao result = underTest.createInstance();

        assertThat(result, instanceOf(ClasspathNotificationProvider.class));
    }
}
