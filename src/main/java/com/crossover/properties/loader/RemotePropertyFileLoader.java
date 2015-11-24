package com.crossover.properties.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class RemotePropertyFileLoader extends PropertyFileLoader {

    @Override
    protected InputStream buildInputStreamFromUri(String uri) throws IOException {
        InputStream inputStream = null;
        URL url = new URL(uri);

        if (null != url) {
            inputStream = url.openConnection().getInputStream();
        }

        return inputStream;
    }
}
