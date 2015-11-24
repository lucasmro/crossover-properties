package com.crossover.trial.properties.type.matcher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AwsRegionMatcherTest {

    private static String AWS_REGION_TYPE = "com.amazonaws.regions.Regions";

    private TypeMatcher matcher;

    @Before
    public void setUp() {
        matcher = new AwsRegionMatcher();
        matcher.setNextMatcher(new StringMatcher());
    }

    @Test
    public void testShouldMatchTheStringTypeWhenTheInputIsASimpleString() {
        String type = matcher.getType("us-west-1");

        Assert.assertEquals(AWS_REGION_TYPE, type);
    }

    @Test
    public void testShouldMatchTheStringTypeWhenTheInputIsAStringContainingNumbers() {
        String type = matcher.getType("eu-central-1");

        Assert.assertEquals(AWS_REGION_TYPE, type);
    }

    @Test
    public void testShouldNotReturnTheAwsRegionTypeWhenTheInputIsNotAValidRegion() {
        String type = matcher.getType("abc");

        Assert.assertNotEquals(AWS_REGION_TYPE, type);
    }
}
