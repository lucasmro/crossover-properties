package com.crossover.properties.loader;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.properties.FileFormat;
import com.crossover.properties.PropertyFile;

public class PropertyFileLoaderTest {

    private PropertyFileLoader propertyFileLoader;

    @Before
    public void setUp() {
        propertyFileLoader = new PropertyFileLoader();
    }

    @Test
    public void testShouldLoadValidPropertiesFileFromClasspath() {
        String URI = "classpath:resources/jdbc.properties";

        String expectedFilename = "jdbc.properties";
        String expectedContent = "username=foo\npassword=bar\nactive=true\n";
        FileFormat expectedFormat = FileFormat.PROPERTIES;

        Optional<PropertyFile> propertyFile = propertyFileLoader.load(URI);

        Assert.assertTrue(propertyFile.isPresent());
        Assert.assertEquals(expectedFilename, propertyFile.get().getFilename());
        Assert.assertEquals(expectedContent, propertyFile.get().getContent());
        Assert.assertEquals(expectedFormat, propertyFile.get().getFormat());
    }

    @Test
    public void testShouldNotThrowAnExceptionWhenFileIsInexistent() {
        String URI = "classpath:resources/fake.properties";

        Optional<PropertyFile> propertyFile = propertyFileLoader.load(URI);

        Assert.assertFalse(propertyFile.isPresent());
    }
}
