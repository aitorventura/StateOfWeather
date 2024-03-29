package es.uji.es.ei1048.weatherApp.integrationTest;


import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;

import es.uji.ei1048.weatherApp.controllerWeather.CurrentWeatherUsingCoordinates;

import es.uji.ei1048.weatherApp.exceptions.NoConnectionException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestCurrentWeatherCoordinates {

    CurrentWeatherUsingCoordinates currentWeatherUsingCoordinates;
    IStore store;
    IWeatherService weatherService;

    @Before
    public void setUp() throws Exception {
        weatherService = mock(IWeatherService.class);
        store = mock(IStore.class);
        currentWeatherUsingCoordinates = new CurrentWeatherUsingCoordinates(store, weatherService);
    }

    @After
    public void tearDown() throws Exception {
        weatherService = null;
        store = null;
        currentWeatherUsingCoordinates = null;
    }

    //Hay datos en la BBDD
    @Test
    public void validCurrentWeatherConsultationUsingACoordinatesWithDataInTheBBDD() {
        CurrentWeather currentWeather = new CurrentWeather();
        Coordinates c = new Coordinates(39.9945711, -0.071089);
        when(store.giveMeTheCurrentWeather( anyDouble(), anyDouble())).thenReturn(currentWeather);
        when(store.giveMeTheCurrentWeather( c.getLon(), c.getLat())).thenReturn(currentWeather);

        currentWeatherUsingCoordinates.giveMeTheCurrentWeatherUsingACoordinates(c.getLon(), c.getLat());

        verify(store, times(1)).removeOldCurrentWeathers();
        verify(store, times(1)).giveMeTheCurrentWeather(c.getLon(), c.getLat());
        verify(weatherService, times(0)).giveMeTheCurrentWeatherUsingCoordinates(c.getLon(), c.getLat());
        verify(store, times(0)).addCurrentWeatherToTheDataBase(any(CurrentWeather.class));

    }

    //Las coordenadas no están en la BBDD con conexión
    @Test
    public void validCurrentWeatherConsultationUsingACoordinatesWithoutDataInTheBBDDWithConnection() {
        Coordinates c = new Coordinates(39.9945711, -0.071089);

        CurrentWeather currentWeather = new CurrentWeather();
        when(store.giveMeTheCurrentWeather(anyString())).thenReturn(null);
        when(weatherService.giveMeTheCurrentWeatherUsingCoordinates(anyDouble(), anyDouble())).thenReturn(null);
        when(weatherService.giveMeTheCurrentWeatherUsingCoordinates(c.getLon(), c.getLat())).thenReturn(currentWeather);

        currentWeatherUsingCoordinates.giveMeTheCurrentWeatherUsingACoordinates(c.getLon(), c.getLat());

        verify(store, times(1)).removeOldCurrentWeathers();
        verify(store, times(1)).giveMeTheCurrentWeather(c.getLon(), c.getLat());
        verify(weatherService, times(1)).giveMeTheCurrentWeatherUsingCoordinates(c.getLon(), c.getLat());
        verify(store, times(1)).addCurrentWeatherToTheDataBase(any(CurrentWeather.class));
    }

    //Las coordenadas no están en la BBDD SIN conexión
    //TODO Mirar las excepciones
    @Test(expected = NoConnectionException.class)
    public void validCurrentWeatherConsultationUsingACoordinatesWithoutDataInTheBBDDWithoutConnection() {
        CurrentWeather currentWeather = new CurrentWeather();
        when(store.giveMeTheCurrentWeather(anyString())).thenReturn(null);
        when(weatherService.giveMeTheCurrentWeatherUsingCoordinates(anyDouble(), anyDouble())).thenReturn(null);
        when(weatherService.giveMeTheCurrentWeatherUsingCoordinates(39.9945711, -0.071089)).thenReturn(null);

        currentWeatherUsingCoordinates.giveMeTheCurrentWeatherUsingACoordinates(39.9945711, -0.071089);

        verify(store, times(1)).removeOldCurrentWeathers();
        verify(store, times(1)).giveMeTheCurrentWeather(39.9945711, -0.071089);
        verify(weatherService, times(1)).giveMeTheCurrentWeatherUsingCoordinates(39.9945711, -0.071089);
        verify(store, times(0)).addCurrentWeatherToTheDataBase(any(CurrentWeather.class));
    }

    
}
