package com.crossover.trial.properties.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;

public class ClasspathPropertyFileLoader extends PropertyFileLoader {

    @Override
    protected InputStream buildInputStreamFromUri(String uri) throws IOException {
        InputStream inputStream = null;
        URL url = ClasspathPropertyFileLoader.class.getClassLoader().getResource(FilenameUtils.getName(uri));

        if (null != url) {
            inputStream = url.openStream();
        }

        return inputStream;
    }
}
