package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.CurrentWeather;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class E2ECurrentWeatherCoordinates extends E2ETestBed {

    @Test
    public void validCurrentWeatherConsultationUsingCoordinates(){
        //Given: coordenadas válidas
        double lon =  39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);



        //When : consulto el tiempo actual para esa ciudad
        CurrentWeather currentWeather = weatherAppFacade.currentWeatherCoordinates(coordinates);


        //Then
        assertTrue(currentWeather.getTemperature() > 5);
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
