package no.bouvet.lambdaws;

import java.util.HashMap;
import java.util.Map;

import static no.bouvet.lambdaws.City.*;

public class WeatherService {

    private static Map<City, Double> todaysTemperature = new HashMap<City, Double>(){{
        put(Boston, 2.3);
        put(SanFrancisco, 2.4);
        put(NewYork, 13.0);
        put(WashingtonDC, 14.0);

    }};


    public static double getTodaysTemperatureForCity(City city){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return todaysTemperature.get(city);
    }
}
