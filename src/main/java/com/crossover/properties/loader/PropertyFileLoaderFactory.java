package com.crossover.properties.loader;

public class PropertyFileLoaderFactory {

    private static final String CLASSPATH = "classpath";

    public PropertyFileLoader getPropertyFileLoader(String uri) {

        if (!uri.startsWith(CLASSPATH)) {
            return new RemotePropertyFileLoader();
        }

        return new ClasspathPropertyFileLoader();
    }
}
