/*
 * Copyright 2015 Eduard Vodicka
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
