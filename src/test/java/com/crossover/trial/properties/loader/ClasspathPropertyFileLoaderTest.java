package com.crossover.trial.properties.loader;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.trial.properties.FileFormat;
import com.crossover.trial.properties.PropertyFile;

public class ClasspathPropertyFileLoaderTest {

    private static String URI_JDBC_PROPERTIES = "classpath:resources/jdbc.properties";
    private static String URI_FAKE_PROPERTIES = "classpath:resources/fake.properties";

    private PropertyFileLoader propertyFileLoader;

    @Before
    public void setUp() {
        propertyFileLoader = new ClasspathPropertyFileLoader();
    }

    @Test
    public void testShouldLoadValidPropertiesFileFromClasspath() {
        String expectedFilename = "jdbc.properties";
        String expectedContent = "username=foo\nemail=foo@bar.com\nactive=true\n";
        FileFormat expectedFormat = FileFormat.PROPERTIES;

        Optional<PropertyFile> propertyFile = propertyFileLoader.load(URI_JDBC_PROPERTIES);

        Assert.assertTrue(propertyFile.isPresent());
        Assert.assertEquals(expectedFilename, propertyFile.get().getFilename());
        Assert.assertEquals(expectedContent, propertyFile.get().getContent());
        Assert.assertEquals(expectedFormat, propertyFile.get().getFormat());
    }

    @Test
    public void testShouldNotThrowAnExceptionWhenFileIsInexistent() {
        Optional<PropertyFile> propertyFile = propertyFileLoader.load(URI_FAKE_PROPERTIES);

        Assert.assertFalse(propertyFile.isPresent());
    }
}
