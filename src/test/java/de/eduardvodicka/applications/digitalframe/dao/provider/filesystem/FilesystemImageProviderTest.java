package de.eduardvodicka.applications.digitalframe.dao.provider.filesystem;

import de.eduardvodicka.applications.digitalframe.dao.AbstractImagesDaoTest;
import org.junit.Before;

/**
 * Created by evodicka on 23.01.2016.
 */
public class FilesystemImageProviderTest extends AbstractImagesDaoTest {

    @Before
    public void init() {
        setUnderTest(new FilesystemImageProvider("./src/test/resources/img"));
    }
}