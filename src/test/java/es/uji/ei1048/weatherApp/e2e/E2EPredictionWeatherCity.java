package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.PredictionWeather;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class E2EPredictionWeatherCity extends E2ETestBed{

    @Test
    public void validPredictionWeatherConsultationUsingACity(){
        //Given: ciudad válida
        String city = "Castellón de la Plana";

        //When : consulto el tiempo de los proximos 3 dias para esa ciudad
        List<PredictionWeather> listPredictionWeather = weatherAppFacade.previsionOfWeatherCity(city);

        //Then
        for(PredictionWeather predictionWeather: listPredictionWeather){
            assertTrue(predictionWeather.getTemperature() > 5);
            assertTrue(predictionWeather.getTemperature() < 25);
        }


    }

    @Test(expected = NotValidCityException.class)
    public void invalidPredictionWeatherConsultationUsingACity(){
        //Given: ciudad no válida
        String city = "Ñkjdskdlas";

        //When : consulto el tiempo de los proximos 3 dias para esa ciudad
        List<PredictionWeather> listPredictionWeather = weatherAppFacade.previsionOfWeatherCity(city);

        //Then: espero que se lance una excepcion

    }
}
