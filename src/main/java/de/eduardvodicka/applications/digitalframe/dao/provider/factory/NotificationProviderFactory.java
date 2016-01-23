package de.eduardvodicka.applications.digitalframe.dao.provider.factory;

import de.eduardvodicka.applications.digitalframe.dao.NotificationDao;
import de.eduardvodicka.applications.digitalframe.dao.provider.classpath.ClasspathNotificationProvider;
import de.eduardvodicka.applications.digitalframe.dao.provider.filesystem.FilesystemNotificationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Spring factory for {@link NotificationDao NotificationDaos}. Creates the correct dao based on the prefix of the
 * property "text.filepath". Can be either "classpath:" or noting (normal file system)
 *
 * @author Eduard Vodicka
 */
@Component
public class NotificationProviderFactory extends AbstractFactoryBean<NotificationDao> {

    @Value("${text.filepath}")
    private String filePath;

    @Override
    public Class<?> getObjectType() {
        return NotificationDao.class;
    }

    @Override
    protected NotificationDao createInstance() {
        if(filePath.startsWith("classpath:")) {
            return new ClasspathNotificationProvider(filePath.split(":")[1]);
        }
        return new FilesystemNotificationProvider(filePath);
    }
}
