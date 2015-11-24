package com.crossover.trial.properties.parser;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.trial.properties.FileFormat;
import com.crossover.trial.properties.Property;
import com.crossover.trial.properties.PropertyFile;
import com.crossover.trial.properties.type.matcher.TypeMatcher;
import com.crossover.trial.properties.type.matcher.TypeMatcherBuilder;

public class JsonParserTest {
    private Parser parser;

    @Before
    public void setUp() {
        TypeMatcher matcher = new TypeMatcherBuilder().buildDefault();
        parser = new JsonParser(matcher);
    }

    @Test
    public void testShouldParseValidPropertiesFile() {
        String filename = "config.json";
        String content = "{\n\"endpoint\":\"http://localhost/v1/\",\n\"timeout\":3600,\n\"active\":true\n,\"factor\":.75\n}";
        FileFormat format = FileFormat.JSON;

        PropertyFile propertyFile = new PropertyFile(filename, content, format);

        Map<String, Property> properties = parser.parse(propertyFile);

        Assert.assertTrue(properties.size() == 4);

        Assert.assertEquals(properties.get("endpoint").getValue(), "http://localhost/v1/");
        Assert.assertEquals(properties.get("timeout").getValue(), "3600");
        Assert.assertEquals(properties.get("active").getValue(), "true");
        Assert.assertEquals(properties.get("factor").getValue(), "0.75");

        Assert.assertEquals(properties.get("endpoint").getType(), "java.lang.String");
        Assert.assertEquals(properties.get("timeout").getType(), "java.lang.Integer");
        Assert.assertEquals(properties.get("active").getType(), "java.lang.Boolean");
        Assert.assertEquals(properties.get("factor").getType(), "java.lang.Double");
    }

    @Test
    public void testShouldNotThrowAnExceptionWhenContentOfFileIsNull() {
        String filename = "blank.json";
        String content = null;
        FileFormat format = FileFormat.JSON;

        PropertyFile propertyFile = new PropertyFile(filename, content, format);

        Map<String, Property> properties = parser.parse(propertyFile);

        Assert.assertTrue(properties.size() == 0);
    }

    @Test
    public void testShouldNotThrowAnExceptionWhenContentOfFileIsEmpty() {
        String filename = "fake.json";
        String content = "";
        FileFormat format = FileFormat.JSON;

        PropertyFile propertyFile = new PropertyFile(filename, content, format);

        Map<String, Property> properties = parser.parse(propertyFile);

        Assert.assertTrue(properties.size() == 0);
    }
}
