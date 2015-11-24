package com.crossover.trial.properties.type.matcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.trial.properties.type.matcher.BooleanMatcher;
import com.crossover.trial.properties.type.matcher.StringMatcher;
import com.crossover.trial.properties.type.matcher.TypeMatcher;

public class BooleanMatcherTest {

    private static String BOOLEAN_TYPE = "java.lang.Boolean";

    private TypeMatcher matcher;

    @Before
    public void setUp() {
        matcher = new BooleanMatcher();
        matcher.setNextMatcher(new StringMatcher());
    }

    @Test
    public void testShouldNotReturnTheBooleanTypeWhenTheInputIsNotAValidBooleanValue() {
        String type = matcher.getType("123");

        Assert.assertNotEquals(BOOLEAN_TYPE, type);
    }

    @Test
    public void testShouldMatchTheStringTrueAsBooleanType() {
        String type = matcher.getType("true");

        Assert.assertEquals(BOOLEAN_TYPE, type);
    }

    @Test
    public void testShouldMatchTheStringFalseAsBooleanType() {
        String type = matcher.getType("false");

        Assert.assertEquals(BOOLEAN_TYPE, type);
    }

    @Test
    public void testShouldMatchWhenTheUppercaseInputStringIsAValidBooleanValue() {
        String type = matcher.getType("TRUE");

        Assert.assertEquals(BOOLEAN_TYPE, type);
    }
}
