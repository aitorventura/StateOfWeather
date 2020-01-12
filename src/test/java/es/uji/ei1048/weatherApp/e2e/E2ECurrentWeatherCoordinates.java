package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class E2ECurrentWeatherCoordinates extends E2ETestBed {

    @Test
    public void validCurrentWeatherConsultationUsingCoordinates(){

        //Given: coordenadas válidas
        double lat =  39.9945711;
        double lon = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);

        //When : consulto el tiempo actual para esa ciudad
        CurrentWeather currentWeather = weatherAppFacade.currentWeatherCoordinates(coordinates);

        //Then
        assertTrue(currentWeather.getTemperature() > 0);
        assertTrue(currentWeather.getTemperature() < 25);

    }

    @Test(expected = NotValidCoordinatesException.class)
    public void invalidCurrentWeatherConsultationUsingCoordinates(){
        //Given: coordenadas no válidas
        double lon =   -500.908;
        double lat = 56.872;
        Coordinates coordinates = new Coordinates(lon, lat);

        //When : consulto el tiempo actual para esa ciudad
        CurrentWeather currentWeather = weatherAppFacade.currentWeatherCoordinates(coordinates);

        //Then: espero que se lance una excepcion

    }
}
