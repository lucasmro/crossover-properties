package com.crossover.trial.properties.type.matcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.trial.properties.type.matcher.IntegerMatcher;
import com.crossover.trial.properties.type.matcher.StringMatcher;
import com.crossover.trial.properties.type.matcher.TypeMatcher;

public class IntegerMatcherTest {

    private static String INTEGER_TYPE = "java.lang.Integer";

    private TypeMatcher matcher;

    @Before
    public void setUp() {
        matcher = new IntegerMatcher();
        matcher.setNextMatcher(new StringMatcher());
    }

    @Test
    public void testShouldNotReturnTheIntegerTypeWhenTheInputIsAString() {
        String type = matcher.getType("abc");

        Assert.assertNotEquals(INTEGER_TYPE, type);
    }

    @Test
    public void testShouldNotReturnTheIntegerTypeWhenTheInputIsNotAValidIntegerValue() {
        String type = matcher.getType("-123-2");

        Assert.assertNotEquals(INTEGER_TYPE, type);
    }

    @Test
    public void testShouldNotReturnTheIntegerTypeWhenTheInputIsADecimalValue() {
        String type = matcher.getType("123.45");

        Assert.assertNotEquals(INTEGER_TYPE, type);
    }

    @Test
    public void testShouldNotReturnTheIntegerTypeWhenTheInputIsABooleanValue() {
        String type = matcher.getType("true");

        Assert.assertNotEquals(INTEGER_TYPE, type);
    }

    @Test
    public void testShouldMatchTheIntegerTypeWhenTheInputValueIs123() {
        String type = matcher.getType("123");

        Assert.assertEquals(INTEGER_TYPE, type);
    }

    @Test
    public void testShouldMatchTheIntegerTypeWhenTheInputValueIsANegativeIntegerNumber() {
        String type = matcher.getType("-123");

        Assert.assertEquals(INTEGER_TYPE, type);
    }

    @Test
    public void testShouldMatchTheIntegerTypeWhenTheInputValueIsZero() {
        String type = matcher.getType("0");

        Assert.assertEquals(INTEGER_TYPE, type);
    }
}
