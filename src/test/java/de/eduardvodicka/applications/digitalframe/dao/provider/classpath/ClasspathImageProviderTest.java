package de.eduardvodicka.applications.digitalframe.dao.provider.classpath;

import de.eduardvodicka.applications.digitalframe.dao.AbstractImagesDaoTest;
import org.junit.Before;

/**
 * Created by evodicka on 23.01.2016.
 */
public class ClasspathImageProviderTest extends AbstractImagesDaoTest {

    @Before
    public void init() {
        setUnderTest(new ClasspathImageProvider("classpath:/img"));
    }
}