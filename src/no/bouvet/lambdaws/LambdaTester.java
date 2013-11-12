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

        //OPPGAVE: Skriv ut alle personer

        population.stream()
                .forEach(System.out::println);

    }

    @Test
    public void printAllAdultMen() {

        //OPPGAVE: Skriv ut alle menn over 18
        //Hint: Person har en isAdult()-metode

        population.stream()
                .filter(c -> c.isAdult())
                .filter(c -> c.getGender() == Person.Gender.MALE)
                .forEach(System.out::println);
    }

    @Test
    public void countAdultMen() {

        //OPPGAVE: Hvor mange menn over 18 år er det?

        long count = population.stream()
                .filter(Citizen::isAdult)
                .filter(c -> c.getGender() == Person.Gender.MALE)
                .count();

        System.out.println(count);
    }

    @Test
    public void sortByAge() {

        //OPPGAVE: Lag en liste (List<Citizen>) som er sortert stigende etter alder

        //Vi kan bruke Collections.sort eller population.sort, men disse vil sortere kilde-listen
        //Collections.sort(population, (p1, p2) -> p1.getAge().compareTo(p2.getAge()));
        //population.sort((p1, p2) -> p1.getAge().compareTo(p2.getAge()));

        List<Citizen> list =  population.stream()
                .sorted((p1, p2) -> p1.getAge().compareTo(p2.getAge()))
                .collect(toList());

        list.stream().forEach(System.out::println);
    }

    @Test
    public void findNameStartingWithJ() {

        //OPPGAVE: Finn navnet på en vilkårlig person som har navn som starter på J

        Optional<String> any = population.stream()
                .map(Citizen::getName)
                .filter(p -> p.startsWith("J"))
                .findAny();

        System.out.println(any.orElse("Fant ingen"));
    }

    @Test
    public void countPopulationByCity(){

        //OPPGAVE: Finn ut hvor mange det bor i hver by (lag en Map<City, Long> som inneholder informasjonen)

        Map<City, Long> collect = population.stream()
                .collect(groupingBy(Person::getCity, counting()));

        collect.forEach((p,e) -> System.out.println(p.getName() + " " + e));

    }

    @Test
    public void namesByCity(){

        //OPPGAVE: Lag en Map<City, List<String>> som inneholder alle byene og navnet til alle som bor i de respektive byene

        Map<City, List<String>> map = population.stream()
                .collect(groupingBy(Person::getCity, mapping(Person::getName, toList())));

        map.forEach((p, e) -> System.out.println(p.getName() + " " + e));
    }
}
