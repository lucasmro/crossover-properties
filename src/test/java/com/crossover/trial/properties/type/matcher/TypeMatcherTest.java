package com.crossover.trial.properties.type.matcher;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import com.crossover.trial.properties.type.matcher.TypeMatcher;

public class TypeMatcherTest {

    @Test
    public void testShouldMatchFoo() {
        TypeMatcher matcher = new FooMatcher();
        String type = matcher.getType("foo");
        Assert.assertEquals("com.crossover.properties.Foo", type);
    }

    @Test
    public void testShouldMatchBar() {
        TypeMatcher matcher = new BarMatcher();
        String type = matcher.getType("bar");
        Assert.assertEquals("com.crossover.properties.Bar", type);
    }

    @Test
    public void testShouldMatchBaz() {
        TypeMatcher matcher = new BazMatcher();
        String type = matcher.getType("baz");
        Assert.assertEquals("com.crossover.properties.Baz", type);
    }

    @Test
    public void testShouldMatchBazSinceItIsTheLastValidOptionInChain() {
        TypeMatcher matcher = new FooMatcher();
        matcher.setNextMatcher(new BarMatcher());
        matcher.setNextMatcher(new BazMatcher());

        String type = matcher.getType("baz");
        Assert.assertEquals("com.crossover.properties.Baz", type);
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
            return "com.crossover.properties.Foo";
        }
    }

    private class BarMatcher extends TypeMatcher {
        @Override
        protected Pattern getPattern() {
            return Pattern.compile("\\bbar\\b");
        }

        @Override
        protected String getName() {
            return "com.crossover.properties.Bar";
        }
    }

    private class BazMatcher extends TypeMatcher {
        @Override
        protected Pattern getPattern() {
            return Pattern.compile("\\bbaz\\b");
        }

        @Override
        protected String getName() {
            return "com.crossover.properties.Baz";
        }
    }
}
