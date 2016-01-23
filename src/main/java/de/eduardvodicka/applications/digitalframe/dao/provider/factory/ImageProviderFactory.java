package de.eduardvodicka.applications.digitalframe.dao.provider.factory;

import de.eduardvodicka.applications.digitalframe.dao.ImagesDao;
import de.eduardvodicka.applications.digitalframe.dao.provider.classpath.ClasspathImageProvider;
import de.eduardvodicka.applications.digitalframe.dao.provider.filesystem.FilesystemImageProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Spring factory for {@link ImagesDao ImageDaos}. Creates the correct dao based on the prefix of the
 * property "text.filepath". Can be either "classpath:" or noting (normal file system)
 *
 * @author Eduard Vodicka
 */
@Component
public class ImageProviderFactory extends AbstractFactoryBean<ImagesDao> {

    @Value("${images.directory}")
    private String directoryPath;

    @Override
    public Class<?> getObjectType() {
        return ImagesDao.class;
    }

    @Override
    protected ImagesDao createInstance() {
        if(directoryPath.startsWith("classpath:")) {
            return new ClasspathImageProvider(directoryPath);
        }
        return new FilesystemImageProvider(directoryPath);
    }
}
