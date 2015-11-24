package com.crossover.properties;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crossover.properties.loader.PropertyFileLoader;
import com.crossover.properties.loader.PropertyFileLoaderFactory;
import com.crossover.properties.parser.Parser;
import com.crossover.properties.parser.ParserFactory;
import com.crossover.properties.type.matcher.BooleanMatcher;
import com.crossover.properties.type.matcher.DoubleMatcher;
import com.crossover.properties.type.matcher.IntegerMatcher;
import com.crossover.properties.type.matcher.StringMatcher;
import com.crossover.properties.type.matcher.TypeMatcher;

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

        // Configure Type Matcher
        TypeMatcher matcher = new BooleanMatcher();
        matcher.setNextMatcher(new IntegerMatcher());
        matcher.setNextMatcher(new DoubleMatcher());
        matcher.setNextMatcher(new StringMatcher());

        // Instantiate Factory
        ParserFactory factory = new ParserFactory(matcher);

        // Populate Sorted Properties Map
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        };
        SortedMap<String, Property> sortedPropertiesMap = new TreeMap<String, Property>(comparator);

        for (String uri : args) {
            PropertyFileLoader loader = new PropertyFileLoaderFactory().getPropertyFileLoader(uri);
            Optional<PropertyFile> propertyFile = loader.load(uri);

            if (propertyFile.isPresent()) {
                Parser parser = factory.getParser(propertyFile.get().getFormat());
                Map<String, Property> propertiesMap = parser.parse(propertyFile.get());
                sortedPropertiesMap.putAll(propertiesMap);
            }
        }

        // Print all properties, type and values
        for (Property property : sortedPropertiesMap.values()) {
            System.out.println(property);
        }
    }
}
