package com.crossover.trial.properties;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import com.crossover.trial.properties.loader.PropertyFileLoader;
import com.crossover.trial.properties.loader.PropertyFileLoaderFactory;
import com.crossover.trial.properties.parser.Parser;
import com.crossover.trial.properties.parser.ParserFactory;
import com.crossover.trial.properties.type.matcher.TypeMatcher;

public class PropertiesManager {

    private final String[] uris;;
    private final TypeMatcher typeMatcher;
    private final ParserFactory parserFactory;

    public PropertiesManager(final String[] uris, final TypeMatcher typeMatcher) {
        this.uris = uris;
        this.typeMatcher = typeMatcher;
        this.parserFactory = new ParserFactory(this.typeMatcher);
    }

    public Collection<Property> getSortedProperties() {

        // Alphabetical order
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        };

        SortedMap<String, Property> sortedPropertiesMap = new TreeMap<String, Property>(comparator);

        // Loop through all URIs
        for (String uri : this.uris) {
            PropertyFileLoader loader = new PropertyFileLoaderFactory().getPropertyFileLoader(uri);
            Optional<PropertyFile> propertyFile = loader.load(uri);

            if (propertyFile.isPresent()) {
                Parser parser = this.parserFactory.getParser(propertyFile.get().getFormat());

                Map<String, Property> propertiesMap = parser.parse(propertyFile.get());
                sortedPropertiesMap.putAll(propertiesMap);
            }
        }

        return sortedPropertiesMap.values();
    }
}
