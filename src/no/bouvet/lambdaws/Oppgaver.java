package no.bouvet.lambdaws;

import org.junit.Test;

import java.util.*;

import static no.bouvet.lambdaws.City.*;

public class Oppgaver {


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

    }

    @Test
    public void printAllAdultMen() {

        //OPPGAVE: Skriv ut alle menn over 18
        //Hint: Person har en isAdult()-metode

    }

    @Test
    public void upperCaseNames() {

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
    public void averageAge() {

        //OPPGAVE: Hva er gjennomsnittsalderen?

    }

    @Test
    public void averageAgeForThoseUnder40() {

        //OPPGAVE: Finn gjennomsnittsalderen for alle under 40

    }

    @Test
    public void countByGender() {

        //OPPGAVE: Finn ut hvor mange det er av hvert kjønn (lag en Map<Gender, Long> som inneholder informasjonen)

    }

    @Test
    public void namesByCity() {

        //OPPGAVE: Lag en Map<City, List<String>> som inneholder alle byene og navnet til de som bor i de respektive byene

    }

    @Test
    public void mostPopularAge() {

        //OPPGAVE: Finn ut hvilken alder som forekommer flest ganger

    }

    @Test
    public void totalPopulationInAllCities() {

        //OPPGAVE: Finn ut hvor mange det bor i alle byene tilsammen (bruk city.getPopulation() for å finne ut hvor mange det bor i en by)
        //Du kan benytte City.asList() for å få tak i alle byene

    }

    @Test
    public void findHighestTemperatureBelow10Degrees() {

        //OPPGAVE: Finn den høyeste temperaturen under 10grader som er målt i alle byene i dag. Finn den raskeste måten å gjøre det på.
        //Du kan benytte City.asList() for å få tak i alle byene, og WeatherService.getTodaysTemperatureForCity() for å få tak i dagens temperatur for én by

    }


}