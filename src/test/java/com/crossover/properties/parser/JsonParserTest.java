package com.crossover.properties.parser;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.properties.FileFormat;
import com.crossover.properties.Property;
import com.crossover.properties.PropertyFile;
import com.crossover.properties.type.matcher.BooleanMatcher;
import com.crossover.properties.type.matcher.DoubleMatcher;
import com.crossover.properties.type.matcher.IntegerMatcher;
import com.crossover.properties.type.matcher.StringMatcher;
import com.crossover.properties.type.matcher.TypeMatcher;

public class JsonParserTest {
    private Parser parser;

    @Before
    public void setUp() {
        TypeMatcher matcher = createMatcher();
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

    private TypeMatcher createMatcher() {
        TypeMatcher matcher = new BooleanMatcher();
        matcher.setNextMatcher(new IntegerMatcher());
        matcher.setNextMatcher(new DoubleMatcher());
        matcher.setNextMatcher(new StringMatcher());

        return matcher;
    }
}
