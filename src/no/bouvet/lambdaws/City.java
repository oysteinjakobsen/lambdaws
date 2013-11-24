package no.bouvet.lambdaws;

import java.util.Arrays;
import java.util.List;


public enum City {
    Boston(4590000L), SanFrancisco(4335391L), NewYork(18897109L), WashingtonDC(5703948L);
    private long population;

    City(long population) {

        this.population = population;
    }

    public long getPopulation() {
        return population;
    }

    public static List<City> asList() {
        return Arrays.asList(values());
    }

}
