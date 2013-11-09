package no.bouvet.lambdaws;

import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.*;

public class LambdaTester {

    City sanFransico = new City("San Francisco", 4335391);
    City newYork = new City("New York City", 18897109);
    City boston = new City("Boston", 4590000);
    City washingtonDC = new City("Washington DC", 5703948);

    List<Citizen> population = new ArrayList<>(Arrays.asList(
            new Citizen("Samuel", 17, Person.Gender.MALE, sanFransico),
            new Citizen("Juliet", 18, Person.Gender.FEMALE, sanFransico),
            new Citizen("Barack", 52, Person.Gender.MALE, newYork),
            new Citizen("John", 25, Person.Gender.MALE, washingtonDC),
            new Citizen("Crystle", 12, Person.Gender.FEMALE, boston)));

    @Test
    public void printAllPersons() {
        //Skriv ut alle personer i population-lista
        population.stream()
                .forEach(System.out::println);

    }

    @Test
    public void printAllAdultMen() {
        population.stream()
                .filter(c -> c.isAdult())
                .filter(c -> c.getGender() == Person.Gender.MALE)
                .forEach(System.out::println);
    }

    @Test
    public void countAdultMen() {

        //Hint: Sjekk ut isAdult()-metoden på Citizen

        long count = population.stream()
                .filter(Citizen::isAdult)
                .filter(c -> c.getGender() == Person.Gender.MALE)
                .count();

        System.out.println(count);
    }

    @Test
    public void sortByAge() {
        //Lag en liste som er sortert stigende etter alder

        //Vi kan bruke Collections.sort eller population.sort, men disse vil sortere population-listen
        //Collections.sort(population, (p1, p2) -> p1.getAge().compareTo(p2.getAge()));
        //population.sort((p1, p2) -> p1.getAge().compareTo(p2.getAge()));
        //population.forEach(System.out::println);

        List<Citizen> list =  population.stream()
                .sorted((p1, p2) -> p1.getAge().compareTo(p2.getAge()))
                .collect(toList());

        list.stream().forEach(System.out::println);
    }

    @Test
    public void findNameStartingWithJ() {
        //Finn navnet på en vilkårlig person som har navn som starter på J

        Optional<String> any = population.stream()
                .map(Citizen::getName)
                .filter(p -> p.startsWith("J"))
                .findAny();

        System.out.println(any.orElse("Fant ingen"));
    }

    @Test
    public void countPopulationByCity(){
        Map<City, Long> collect = population.stream()
                .collect(groupingBy(Person::getCity, counting()));

        collect.forEach((p,e) -> System.out.println(p.getName() + " " + e));

    }

    @Test
    public void namesByCity(){
        Map<City, List<String>> map = population.stream()
                .collect(groupingBy(Person::getCity, mapping(Person::getName, toList())));

        map.forEach((p, e) -> System.out.println(p.getName() + " " + e));
    }
}
