package com.crossover.properties.loader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.properties.FileFormat;
import com.crossover.properties.PropertyFile;
import com.crossover.properties.loader.PropertyFileLoader;

public class PropertyFileLoaderTest {

    private PropertyFileLoader propertyFileLoader;

    @Before
    public void setUp() {
        propertyFileLoader = new PropertyFileLoader();
    }

    @Test
    public void testShoudLoadValidPropertiesFileFromClasspath() {
        String URI = "classpath:resources/jdbc.properties";

        String expectedFilename = "jdbc.properties";
        String expectedContent = "username=foo\npassword=bar\nactive=true\n";
        FileFormat expectedFormat = FileFormat.PROPERTIES;

        PropertyFile propertyFile = propertyFileLoader.load(URI);

        Assert.assertEquals(expectedFilename, propertyFile.getFilename());
        Assert.assertEquals(expectedContent, propertyFile.getContent());
        Assert.assertEquals(expectedFormat, propertyFile.getFormat());
    }
}
