package no.bouvet.lambdaws;

import org.junit.Test;

import java.util.*;

public class Oppgaver {


    City sanFransico = new City("San Francisco", 4335391);
    City newYork = new City("New York City", 18897109);
    City boston = new City("Boston", 4590000);
    City washingtonDC = new City("Washington DC", 5703948);

    List<Citizen> population = new ArrayList<>(Arrays.asList(
            new Citizen("Samuel", 17, Person.Gender.MALE, sanFransico),
            new Citizen("Juliet", 18, Person.Gender.FEMALE, sanFransico),
            new Citizen("Barack", 52, Person.Gender.MALE, newYork),
            new Citizen("John", 25, Person.Gender.MALE, washingtonDC),
            new Citizen("Crystle", 12, Person.Gender.FEMALE, boston),
            new Citizen("Bill", 18, Person.Gender.MALE, boston)));

    @Test
    public void printAllPersons() {

        //OPPGAVE: Skriv ut alle personer

    }

    @Test
    public void printAllAdultMen() {

        //OPPGAVE: Skriv ut alle menn over 18
        //Hint: Person har en isAdult()-metode

    }

    @Test
    public void upperCaseNames(){

        //OPPGAVE: Skriv ut alle navn i UPPER CASE

    }

    @Test
    public void countAdultMen() {

        //OPPGAVE: Hvor mange menn over 18 år er det?

    }

    @Test
    public void sortByAge() {

        //OPPGAVE: Lag en liste (List<Citizen>) som er sortert stigende etter alder

    }


    @Test
    public void findNameStartingWithJ() {

        //OPPGAVE: Finn navnet på en vilkårlig person som har navn som starter på J

    }

    @Test
    public void allUniqueAges() {

        //OPPGAVE: Finn alle unike aldre

    }

    @Test
    public void averageAge(){

        //OPPGAVE: Hva er gjennomsnittsalderen?

    }

    @Test
    public void averageAgeForThoseUnder40(){

        //OPPGAVE: Finn gjennomsnittsalderen for alle innbyggerne under 40

    }

    @Test
    public void countPopulationByCity(){

        //OPPGAVE: Finn ut hvor mange det bor i hver by (lag en Map<City, Long> som inneholder informasjonen)

    }

    @Test
    public void namesByCity(){

        //OPPGAVE: Lag en Map<City, List<String>> som inneholder alle byene og navnet til alle som bor i de respektive byene

    }

    @Test
    public void mostPopularAge(){

        //OPPGAVE: Finn ut hvilken alder som forekommer flest ganger

    }
}
