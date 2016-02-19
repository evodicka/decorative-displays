package de.eduardvodicka.applications.digitalframe.test;

import de.eduardvodicka.applications.digitalframe.dao.provider.factory.ImageProviderFactory;
import de.eduardvodicka.applications.digitalframe.dao.provider.factory.NotificationProviderFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Created by evodicka on 02.01.2016.
 */
@Configuration
public class ClasspathConfiguration {
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setIgnoreResourceNotFound(true);

        Properties properties = new Properties();
        properties.setProperty("images.directory", "classpath:/somedir");
        properties.setProperty("text.filepath", "classpath:/someOtherDir.txt");
        ppc.setProperties(properties);

        return ppc;
    }

    @Bean
    ImageProviderFactory getTestBean() {
        return new ImageProviderFactory();
    }

    @Bean
    NotificationProviderFactory getNotificationFactory() {
        return new NotificationProviderFactory();
    }
}
