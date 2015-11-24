package com.crossover.properties.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crossover.properties.Property;
import com.crossover.properties.PropertyFile;
import com.crossover.properties.type.matcher.TypeMatcher;

public class PropertiesParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesParser.class);

    private static final String EMPTY = "";

    private final TypeMatcher matcher;

    public PropertiesParser(TypeMatcher matcher) {
        this.matcher = matcher;
    }

    public Map<String, Property> parse(PropertyFile propertyFile) {
        Map<String, Property> map = new HashMap<String, Property>();

        try {
            Properties properties = new Properties();

            if (null != propertyFile && null != propertyFile.getContent()) {
                StringReader stringReader = new StringReader(propertyFile.getContent());
                properties.load(stringReader);
            }

            Enumeration<?> keys = properties.propertyNames();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                String value = properties.getProperty(key);

                if (EMPTY.equalsIgnoreCase(key) || EMPTY.equalsIgnoreCase(value)) {
                    continue;
                }

                String type = matcher.getType(value);

                map.put(key, new Property(key, value, type));
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return map;
    }
}
