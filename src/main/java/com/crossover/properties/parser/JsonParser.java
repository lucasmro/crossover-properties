package com.crossover.properties.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crossover.properties.Property;
import com.crossover.properties.PropertyFile;
import com.crossover.properties.type.matcher.TypeMatcher;

public class JsonParser implements Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParser.class);

    private final TypeMatcher matcher;

    public JsonParser(TypeMatcher matcher) {
        this.matcher = matcher;
    }

    public Map<String, Property> parse(PropertyFile propertyFile) {
        Map<String, Property> map = new HashMap<String, Property>();

        try {
            JSONObject jsonObject = new JSONObject();

            if (null != propertyFile && null != propertyFile.getContent() && !propertyFile.getContent().isEmpty()) {
                jsonObject = new JSONObject(propertyFile.getContent());
            }

            Iterator<?> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = jsonObject.get(key).toString();
                String type = matcher.getType(value);

                map.put(key, new Property(key, value, type));
            }

        } catch (JSONException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return map;
    }
}
