package no.bouvet.lambdaws;

public class City {
    private String name;
    private long population;

    public City(String name, long population) {
        this.name = name;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return name + '(' + population + ')';
    }
}
