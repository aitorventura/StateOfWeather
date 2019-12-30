package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class E2ECurrentWeatherCity extends E2ETestBed {

    @Test
    public void validCurrentWeatherConsultationUsingACity(){
        //Given: ciudad válida
        String city = "Castellón de la Plana";

        //When : consulto el tiempo actual para esa ciudad
        CurrentWeather currentWeather = weatherAppFacade.currentWeatherCity(city);

        //Then
        assertTrue(currentWeather.getTemperature() > 5);
        assertTrue(currentWeather.getTemperature() < 25);

    }

    @Test(expected = NotValidCityException.class)
    public void invalidCurrentWeatherConsultationUsingACity(){
        //Given: ciudad no válida
        String city = "Ñkjdskdlas";

        //When : consulto el tiempo actual para esa ciudad
        CurrentWeather currentWeather = weatherAppFacade.currentWeatherCity(city);

        //Then: espero que se lance una excepcion

    }
}
