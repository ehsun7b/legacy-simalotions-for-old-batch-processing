package ehsun7b;

import java.util.Random;

public class AddressGenerator {
    private Random random;

    public AddressGenerator(Random random) {
        this.random = random;
    }

    private String[] streetNames = {
            "Maple Street", "Main Street", "Oak Avenue", "Elm Road", "Cedar Lane", "Pine Court", "Birch Drive",
            "Willow Way", "Rose Garden", "Meadow Lane", "Sunset Boulevard", "River View", "Greenwood Terrace",
    };

    private String[] cityNames = {
            "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego",
            "Dallas", "San Jose", "Austin", "Jacksonville", "San Francisco", "Seattle", "Denver",
            // Add more city names as needed
    };

    private String[] stateNames = {
            "California", "Texas", "Florida", "New York", "Pennsylvania", "Illinois", "Ohio", "Georgia", "North Carolina",
            "Michigan", "New Jersey", "Virginia", "Washington", "Arizona", "Massachusetts",
            // Add more state names as needed
    };

    public String generateAddress() {
        final var streetName = streetNames[random.nextInt(streetNames.length)];
        final var cityName = cityNames[random.nextInt(cityNames.length)];
        final var stateName = stateNames[random.nextInt(stateNames.length)];
        final int houseNumber = random.nextInt(999) + 1;

        return houseNumber + " " + streetName + "|" + cityName + "|" + stateName;
    }
}
