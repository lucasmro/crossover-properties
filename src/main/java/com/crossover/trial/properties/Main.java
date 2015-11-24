package com.crossover.trial.properties;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crossover.trial.properties.type.matcher.TypeMatcher;
import com.crossover.trial.properties.type.matcher.TypeMatcherBuilder;

/**
 * This is the entry point of the project
 *
 * @author Lucas Michelini Reis de Oliveira
 */
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /**
     * The main method responsible to start the project
     *
     * @param args
     *            - URIs containing the properties
     */
    public static void main(String[] args) {
        LOGGER.debug("Started");

        if (args.length == 0) {
            LOGGER.error("No arguments");
        }

        // Configure Type Matcher (Chain)
        TypeMatcher typeMatcher = new TypeMatcherBuilder().buildDefault();

        // Get all alphabetically sorted properties from all URIs
        Collection<Property> sortedProperties = new PropertiesManager(args, typeMatcher).getSortedProperties();

        // Print all properties, types and values
        for (Property property : sortedProperties) {
            System.out.println(property);
        }
    }
}
