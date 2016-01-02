package de.eduardvodicka.applications.digitalframe.dao.provider.factory;

import de.eduardvodicka.applications.digitalframe.dao.provider.NotificationProvider;
import de.eduardvodicka.applications.digitalframe.dao.provider.classpath.ClasspathNotificationProvider;
import de.eduardvodicka.applications.digitalframe.dao.provider.filesystem.FilesystemNotificationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by evodicka on 02.01.2016.
 */
@Component
public class NotificationProviderFactory extends AbstractFactoryBean<NotificationProvider> {

    @Value("${text.filepath}")
    private String filePath;

    @Override
    public Class<?> getObjectType() {
        return NotificationProvider.class;
    }

    @Override
    protected NotificationProvider createInstance() throws Exception {
        if(filePath.startsWith("classpath:")) {
            return new ClasspathNotificationProvider(filePath.split(":")[1]);
        }
        return new FilesystemNotificationProvider(filePath);
    }
}
