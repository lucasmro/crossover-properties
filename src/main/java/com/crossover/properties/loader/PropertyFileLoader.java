package com.crossover.properties.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crossover.properties.FileFormat;
import com.crossover.properties.PropertyFile;

public abstract class PropertyFileLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFileLoader.class);

    public Optional<PropertyFile> load(String uri) {
        String content = null;
        InputStream inputStream = null;
        PropertyFile propertyFile = null;

        try {
            inputStream = buildInputStreamFromUri(uri);
            content = inputStreamToString(inputStream);

            String filename = FilenameUtils.getName(uri);
            String extension = FilenameUtils.getExtension(uri).toUpperCase();
            FileFormat format = FileFormat.valueOf(extension);

            propertyFile = new PropertyFile(filename, content, format);
        } catch (IOException e) {
            LOGGER.error("Failed to load URI", e);
        } finally {
            closeInputStream(inputStream);
        }

        return Optional.ofNullable(propertyFile);
    }

    abstract protected InputStream buildInputStreamFromUri(String uri) throws IOException;

    private final String inputStreamToString(InputStream inputStream) throws IOException {
        if (null == inputStream) {
            throw new IOException();
        }

        String input;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        StringBuilder contentBuilder = new StringBuilder();

        while ((input = br.readLine()) != null) {
            contentBuilder.append(input);
            contentBuilder.append(System.lineSeparator());
        }

        br.close();

        return contentBuilder.toString();
    }

    private void closeInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
