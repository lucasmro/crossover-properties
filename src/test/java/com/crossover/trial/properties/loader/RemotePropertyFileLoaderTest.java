package com.crossover.trial.properties.loader;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.crossover.trial.properties.FileFormat;
import com.crossover.trial.properties.PropertyFile;

public class RemotePropertyFileLoaderTest {

    private static String URI_FILE_CONFIG_JSON = "file:///tmp//config.json";
    private static String URI_FILE_FAKE_JSON = "file:///tmp//fake.json";

    private PropertyFileLoader propertyFileLoader;

    @Before
    public void setUp() {
        propertyFileLoader = new RemotePropertyFileLoader();
    }

    @Ignore
    @Test
    public void testShouldLoadValidPropertiesFileFromFilePath() {
        String expectedFilename = "config.json";
        String expectedContent = "{\n\"endpoint\":\"http://localhost/v1/\",\n\"timeout\":3600,\n\"active\":true\n,\"factor\":.75\n}";
        FileFormat expectedFormat = FileFormat.JSON;

        Optional<PropertyFile> propertyFile = propertyFileLoader.load(URI_FILE_CONFIG_JSON);

        Assert.assertTrue(propertyFile.isPresent());
        Assert.assertEquals(expectedFilename, propertyFile.get().getFilename());
        Assert.assertEquals(expectedContent, propertyFile.get().getContent());
        Assert.assertEquals(expectedFormat, propertyFile.get().getFormat());
    }

    @Ignore
    @Test
    public void testShouldNotThrowAnExceptionWhenFileIsInexistent() {
        Optional<PropertyFile> propertyFile = propertyFileLoader.load(URI_FILE_FAKE_JSON);

        Assert.assertFalse(propertyFile.isPresent());
    }
}
