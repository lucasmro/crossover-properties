package com.crossover.trial.properties.type.matcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringMatcherTest {

    private static String STRING_TYPE = "java.lang.String";

    private TypeMatcher matcher;

    @Before
    public void setUp() {
        matcher = new StringMatcher();
    }

    @Test
    public void testShouldMatchTheStringTypeWhenTheInputIsASimpleString() {
        String type = matcher.getType("Hello World!");

        Assert.assertEquals(STRING_TYPE, type);
    }

    @Test
    public void testShouldMatchTheStringTypeWhenTheInputIsAStringContainingNumbers() {
        String type = matcher.getType("12345");

        Assert.assertEquals(STRING_TYPE, type);
    }

    @Test
    public void testShouldMatchTheStringTypeWhenTheInputIsAStringContainingABooleanValue() {
        String type = matcher.getType("true");

        Assert.assertEquals(STRING_TYPE, type);
    }
}
