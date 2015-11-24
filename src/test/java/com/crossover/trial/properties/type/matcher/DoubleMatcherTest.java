package com.crossover.trial.properties.type.matcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.crossover.trial.properties.type.matcher.DoubleMatcher;
import com.crossover.trial.properties.type.matcher.StringMatcher;
import com.crossover.trial.properties.type.matcher.TypeMatcher;

public class DoubleMatcherTest {

    private static String DOUBLE_TYPE = "java.lang.Double";

    private TypeMatcher matcher;

    @Before
    public void setUp() {
        matcher = new DoubleMatcher();
        matcher.setNextMatcher(new StringMatcher());
    }

    @Test
    public void testShouldNotReturnTheDoubleTypeWhenTheInputIsAString() {
        String type = matcher.getType("abc");

        Assert.assertNotEquals(DOUBLE_TYPE, type);
    }

    @Test
    public void testShouldNotReturnTheDoubleTypeWhenTheInputIsAnIntegerValue() {
        String type = matcher.getType("123");

        Assert.assertNotEquals(DOUBLE_TYPE, type);
    }

    @Test
    public void testShouldNotReturnTheDoubleTypeWhenTheInputIsABooleanValue() {
        String type = matcher.getType("true");

        Assert.assertNotEquals(DOUBLE_TYPE, type);
    }

    @Test
    public void testShouldMatchTheDoubleTypeWhenTheInputIsAValidDecimalValue() {
        String type = matcher.getType("123.45");

        Assert.assertEquals(DOUBLE_TYPE, type);
    }

    @Test
    public void testShouldMatchTheDoubleTypeWhenTheInputValueIsANegativeDecimalNumber() {
        String type = matcher.getType("-123.00");

        Assert.assertEquals(DOUBLE_TYPE, type);
    }

    @Test
    public void testShouldMatchTheDoubleTypeWhenTheInputValueIsALeadingZero() {
        String type = matcher.getType(".0");

        Assert.assertEquals(DOUBLE_TYPE, type);
    }

    @Test
    public void testShouldMatchTheDoubleTypeWhenTheInputValueContainsANumberInScientificNotation() {
        String type = matcher.getType("1.8281e-009");

        Assert.assertEquals(DOUBLE_TYPE, type);
    }
}
