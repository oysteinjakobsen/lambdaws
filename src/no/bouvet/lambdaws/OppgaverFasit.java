package no.bouvet.lambdaws;

import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.*;
import static no.bouvet.lambdaws.City.*;

public class OppgaverFasit {

    List<Citizen> population = new ArrayList<>(Arrays.asList(
            new Citizen("Samuel", 17, Person.Gender.MALE, SanFrancisco),
            new Citizen("Juliet", 18, Person.Gender.FEMALE, SanFrancisco),
            new Citizen("Barack", 52, Person.Gender.MALE, NewYork),
            new Citizen("John", 25, Person.Gender.MALE, WashingtonDC),
            new Citizen("Crystle", 12, Person.Gender.FEMALE, Boston),
            new Citizen("Bill", 18, Person.Gender.MALE, Boston)));

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
    public void upperCaseNames(){

        //OPPGAVE: Skriv ut alle navn i UPPER CASE

        population.stream()
                .map(Person::getName)
                .map(p -> p.toUpperCase())
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
                .sorted(stigendeAlder)
                .collect(toList());

        list.stream().forEach(System.out::println);
    }

    Comparator<Person> stigendeAlder = (a, b) -> Integer.compare(
            a.getAge(), b.getAge());

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
    public void allUniqueAges() {

        //OPPGAVE: Finn alle unike aldre

        Set<Integer> uniqueAges = population.stream()
                .map(Person::getAge)
                .collect(toSet());

        uniqueAges.forEach(System.out::println);

    }

    @Test
    public void averageAge(){

        //OPPGAVE: Hva er gjennomsnittsalderen?

        OptionalDouble averageAge = population.stream()
                .mapToInt(Person::getAge)
                .average();

        averageAge.ifPresent(System.out::println);

    }

    @Test
    public void averageAgeForThoseUnder40(){

        //OPPGAVE: Finn gjennomsnittsalderen for alle under 40

        OptionalDouble averageAge = population.stream()
                .mapToInt(Person::getAge)
                .filter(p -> p < 40).average();

        averageAge.ifPresent(System.out::println);
    }

    @Test
    public void countByGender(){

        //OPPGAVE: Finn ut hvor mange det er av hvert kjønn (lag en Map<Gender, Long> som inneholder informasjonen)

        Map<Person.Gender, Long> collect = population.stream()
                .collect(groupingBy(Person::getGender, counting()));

        collect.forEach((p,e) -> System.out.println(p + " " + e));

    }

    @Test
    public void namesByCity(){

        //OPPGAVE: Lag en Map<City, List<String>> som inneholder alle byene og navnet til de som bor i de respektive byene

        Map<City, List<String>> map = population.stream()
                .collect(groupingBy(Person::getCity, mapping(Person::getName, toList())));

        map.forEach((p, e) -> System.out.println(p + " " + e));
    }

    @Test
    public void mostPopularAge(){

        //OPPGAVE: Finn ut hvilken alder som forekommer flest ganger

        Map<Integer, Long> map = population.stream()
                .collect(groupingBy(Person::getAge, counting()));

        map.entrySet().stream()
                .max(Map.Entry.<Integer, Long>comparingByValue())
                .map(e -> e.getKey())
                .ifPresent(System.out::println);

    }

    @Test
    public void totalPopulationInAllCities(){

        //OPPGAVE: Finn ut hvor mange det bor i alle byene tilsammen (bruk city.getPopulation() for å finne ut hvor mange det bor i en by)
        //Du kan benytte City.asList() for å få tak i alle byene

        Long totalPopulation = City.asList().stream()
                .mapToLong(City::getPopulation)
                .sum();

        //Evt:

        Long totalPopulationByReduce = City.asList().stream()
                .map(City::getPopulation)
                .reduce(0l, (x, y) -> x + y);

        System.out.println(totalPopulation);
        System.out.println(totalPopulationByReduce);
    }

    @Test
    public void findHighestTemperatureBelow10Degrees() {

        //OPPGAVE: Finn den høyeste temperaturen under 10grader som er målt i alle byene i dag. Finn den raskeste måten å gjøre det på.
        //Du kan benytte City.asList() for å få tak i alle byene, og WeatherService.getTodaysTemperatureForCity() for å få tak i dagens temperatur for én by


        //Sekvensiell:
        long start = System.nanoTime();
        Double highestTemperatureBelow10Degrees = City.asList()
                .stream()
                .map(WeatherService::getTodaysTemperatureForCity)
                .filter(t -> t < 10.0)
                .reduce(0.0, OppgaverFasit::pickHigh);

        long end = System.nanoTime();
        System.out.println("Highest temperature over 10.0: " + highestTemperatureBelow10Degrees);
        System.out.println("Time: " + (end - start)/1.0e9);

        //Parallell:
        long startParallel = System.nanoTime();
        highestTemperatureBelow10Degrees = City.asList()
                .parallelStream()
                .map(WeatherService::getTodaysTemperatureForCity)
                .filter(t -> t < 10.0)
                .reduce(0.0, OppgaverFasit::pickHigh);

        long endParallel = System.nanoTime();
        System.out.println("Highest temperature over 10.0: " + highestTemperatureBelow10Degrees);
        System.out.println("Parallel Time: " + (endParallel - startParallel)/1.0e9);

    }


    private static double pickHigh(
            final double temperature1, final double temperature2) {

        return temperature1 > temperature2 ? temperature1 : temperature2;
    }
}
