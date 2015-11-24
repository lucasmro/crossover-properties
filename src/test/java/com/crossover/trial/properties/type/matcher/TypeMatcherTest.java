package com.crossover.trial.properties.type.matcher;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class TypeMatcherTest {

    private static String NAMESPACE_FOO = "com.crossover.properties.Foo";
    private static String NAMESPACE_BAR = "com.crossover.properties.Bar";
    private static String NAMESPACE_BAZ = "com.crossover.properties.Baz";

    @Test
    public void testShouldMatchFoo() {
        TypeMatcher matcher = new FooMatcher();
        String type = matcher.getType("foo");
        Assert.assertEquals(NAMESPACE_FOO, type);
    }

    @Test
    public void testShouldMatchBar() {
        TypeMatcher matcher = new BarMatcher();
        String type = matcher.getType("bar");
        Assert.assertEquals(NAMESPACE_BAR, type);
    }

    @Test
    public void testShouldMatchBaz() {
        TypeMatcher matcher = new BazMatcher();
        String type = matcher.getType("baz");
        Assert.assertEquals(NAMESPACE_BAZ, type);
    }

    @Test
    public void testShouldMatchBazSinceItIsTheLastValidOptionInChain() {
        TypeMatcher matcher = new FooMatcher();
        matcher.setNextMatcher(new BarMatcher());
        matcher.setNextMatcher(new BazMatcher());

        String type = matcher.getType("baz");
        Assert.assertEquals(NAMESPACE_BAZ, type);
    }

    @Test(expected = NullPointerException.class)
    public void testShouldThrowNullPointerExceptionSinceTheInputDoesNotCorrespondToAnyValidPattern() {
        TypeMatcher matcher = new FooMatcher();
        matcher.getType("banana");
    }

    private class FooMatcher extends TypeMatcher {
        @Override
        protected Pattern getPattern() {
            return Pattern.compile("\\bfoo\\b");
        }

        @Override
        protected String getName() {
            return NAMESPACE_FOO;
        }
    }

    private class BarMatcher extends TypeMatcher {
        @Override
        protected Pattern getPattern() {
            return Pattern.compile("\\bbar\\b");
        }

        @Override
        protected String getName() {
            return NAMESPACE_BAR;
        }
    }

    private class BazMatcher extends TypeMatcher {
        @Override
        protected Pattern getPattern() {
            return Pattern.compile("\\bbaz\\b");
        }

        @Override
        protected String getName() {
            return NAMESPACE_BAZ;
        }
    }
}
