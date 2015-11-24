package com.crossover.properties.loader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PropertyFileLoaderFactoryTest {

    private PropertyFileLoaderFactory factory;

    @Before
    public void setUp() {
        factory = new PropertyFileLoaderFactory();
    }

    @Test
    public void testShouldReturnAnInstanceOfClasspathPropertyFileLoaderWhenUriRefersToClasspath() {
        String uri = "classpath:resources/jdbc.properties";
        PropertyFileLoader loader = factory.getPropertyFileLoader(uri);
        Assert.assertTrue(loader instanceof ClasspathPropertyFileLoader);
    }

    @Test
    public void testShouldReturnAnInstanceOfRemotePropertyFileLoaderWhenUriRefersToFilePath() {
        String uri = "file:///tmp//config.json";
        PropertyFileLoader loader = factory.getPropertyFileLoader(uri);
        Assert.assertTrue(loader instanceof RemotePropertyFileLoader);
    }

    @Test
    public void testShouldReturnAnInstanceOfRemotePropertyFileLoaderWhenUriRefersToRemotePath() {
        String uri = "http://localhost/global/config.json";
        PropertyFileLoader loader = factory.getPropertyFileLoader(uri);
        Assert.assertTrue(loader instanceof RemotePropertyFileLoader);
    }

}
