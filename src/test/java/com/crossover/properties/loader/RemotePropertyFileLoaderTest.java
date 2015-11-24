package com.crossover.properties.loader;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.crossover.properties.FileFormat;
import com.crossover.properties.PropertyFile;

public class RemotePropertyFileLoaderTest {

    private PropertyFileLoader propertyFileLoader;

    @Before
    public void setUp() {
        propertyFileLoader = new RemotePropertyFileLoader();
    }

    @Ignore
    @Test
    public void testShouldLoadValidPropertiesFileFromFilePath() {
        String URI = "file:///tmp//config.json";

        String expectedFilename = "config.json";
        String expectedContent = "{\n\"endpoint\":\"http://localhost/v1/\",\n\"timeout\":3600,\n\"active\":true\n,\"factor\":.75\n}";
        FileFormat expectedFormat = FileFormat.JSON;

        Optional<PropertyFile> propertyFile = propertyFileLoader.load(URI);

        Assert.assertTrue(propertyFile.isPresent());
        Assert.assertEquals(expectedFilename, propertyFile.get().getFilename());
        Assert.assertEquals(expectedContent, propertyFile.get().getContent());
        Assert.assertEquals(expectedFormat, propertyFile.get().getFormat());
    }

    @Ignore
    @Test
    public void testShouldNotThrowAnExceptionWhenFileIsInexistent() {
        String URI = "file:///tmp//fake.json";

        Optional<PropertyFile> propertyFile = propertyFileLoader.load(URI);

        Assert.assertFalse(propertyFile.isPresent());
    }
}
