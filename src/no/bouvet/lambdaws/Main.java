package no.bouvet.lambdaws;

import no.bouvet.lambdaws.Person.Gender;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

    private static interface Test<T> {
        boolean test(T t);
    }

    @FunctionalInterface
    private static interface Service<T> {
        T get(Long id);
    }

    public static void main(String[] args) {
        List<Person> citizens = initPopulation();

        // Ekstern iterator
        // for (Person person : citizens) {
        // System.out.println(person.getName());
        // }

        // Intern iterator
        citizens.forEach(new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println(person);
            }
        });

        citizens.forEach((p) -> {
            System.out.println(p);
        });
        // citizens.forEach(System.out::println);

        long antall = citizens.stream()
                .filter(c -> c.isAdult())
                .filter(c -> c.getGender() == Gender.MALE)
                .count();


        Predicate<Person> voksen = new Predicate<Person>() {
            @Override
            public boolean test(Person p) {
                return p.isAdult();
            }
        };

        // Predicate<Person> voksen = p -> p.isAdult();
        Predicate<Person> mann = p -> p.getGender() == Gender.MALE;
        Comparator<Person> stigendeAlder = (a, b) -> Integer.compare(
                a.getAge(), b.getAge());
        Consumer<Person> skrivUt = p -> System.out.println(p);

        citizens.stream()
                .filter(voksen)
                .filter(mann)
                .sorted(stigendeAlder)
                .forEach(skrivUt);

        double a = citizens.stream()
                .filter(voksen)
                .filter(mann)
                .map(p -> p.getCity())
                .distinct()
                .mapToLong(c -> c.getPopulation())
                .average()
                .orElse(0);

        System.out.println(antall);
        System.out.println(a);

        citizens.stream().map(Person::getAge).forEach(System.out::println);
    }


    private static List<Person> initPopulation() {
        List<Person> population = new ArrayList<>();

        City sanFransico = new City("San Francisco", 4335391);
        City newYork = new City("New York City", 18897109);
        City boston = new City("Boston", 4590000);
        City washingtonDC = new City("Washington DC", 5703948);

        population.add(new Citizen("Samuel", 17, Gender.MALE, sanFransico));
        population.add(new Citizen("Juliet", 18, Gender.FEMALE, sanFransico));
        population.add(new Citizen("Barack", 52, Gender.MALE, newYork));
        population.add(new Citizen("John", 25, Gender.MALE, washingtonDC));
        population.add(new Citizen("Crystle", 12, Gender.FEMALE, boston));

        return population;
    }
}
