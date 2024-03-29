package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.PredictionWeather;
import es.uji.ei1048.weatherApp.controllerWeather.PredictionWeatherUsingCoordinates;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestPredictionWeatherUsingCoordinates {
    PredictionWeatherUsingCoordinates predictionWeatherUsingCoordinates;
    IStore store;
    IWeatherService weatherService;

    @Before
    public void setUp() throws Exception {
        weatherService = mock(IWeatherService.class);
        store = mock(IStore.class);
        predictionWeatherUsingCoordinates = new PredictionWeatherUsingCoordinates(store, weatherService);
    }

    @After
    public void tearDown() throws Exception {
        weatherService = null;
        store = null;
        predictionWeatherUsingCoordinates = null;
    }

    //Hay datos en la BBDD
    @Test
    public void validPredictionWeatherConsultationUsingCoordinatesWithDataInTheDB() {
        Coordinates c = new Coordinates(39.9945711, -0.071089);

        ArrayList predictionWeatherList = new ArrayList<PredictionWeather>();
        for (int i = 0; i < 3; i++) {
            predictionWeatherList.add(new PredictionWeather());
        }

        when(store.giveMeTheListOfPredictionWeatherUsingCoordinates(c.getLon(), c.getLat())).thenReturn(predictionWeatherList);

        predictionWeatherUsingCoordinates.giveMeThePredictionToTheseCoordinates(new Coordinates(c.getLon(), c.getLat()));

        verify(store, times(1)).removeOldPredicionWeathers();
        verify(store, times(1)).giveMeTheListOfPredictionWeatherUsingCoordinates(c.getLon(), c.getLat());
        verify(weatherService, times(0)).giveMeTheListOfPredictionsUsingCoordinates(c.getLon(), c.getLat());
        verify(store, times(0)).addPredictionWeatherToTheDataBase(any(PredictionWeather.class));
    }

    //La ciudad no está en la BBDD con conexión
    @Test
    public void validPredictionWeatherConsultationUsingCoordinatesWithoutDataInTheBBDDWithConnection() {
        Coordinates c = new Coordinates(39.9945711, -0.071089);
        ArrayList <PredictionWeather> predictionWeatherList = new ArrayList<PredictionWeather>();
        for (int i = 0; i < 3; i++) {
            predictionWeatherList.add(new PredictionWeather());
        }
        when(store.giveMeTheListOfPredictionWeatherUsingCoordinates(c.getLon(), c.getLat())).thenReturn(null);

        when(weatherService.giveMeTheListOfPredictionsUsingCoordinates(anyDouble(), -anyDouble())).thenReturn(null);
        when(weatherService.giveMeTheListOfPredictionsUsingCoordinates(c.getLon(), c.getLat())).thenReturn(predictionWeatherList);

        predictionWeatherUsingCoordinates.giveMeThePredictionToTheseCoordinates(new Coordinates(c.getLon(), c.getLat()));

        verify(store, times(1)).removeOldPredicionWeathers();
        verify(store, times(1)).giveMeTheListOfPredictionWeatherUsingCoordinates(c.getLon(), c.getLat());
        verify(weatherService, times(1)).giveMeTheListOfPredictionsUsingCoordinates(c.getLon(), c.getLat());
        verify(store, times(3)).addPredictionWeatherToTheDataBase(any(PredictionWeather.class));
    }

    //La ciudad no está en la BBDD SIN conexión
    @Test(expected = NotValidCoordinatesException.class)
    public void validPredictionWeatherConsultationUsingCoordinatesWithoutDataInTheBBDDWithoutConnection() {
        ArrayList predictionWeatherList = new ArrayList<PredictionWeather>();
        for (int i = 0; i < 3; i++) {
            predictionWeatherList.add(new PredictionWeather());
        }
        when(store.giveMeTheListOfPredictionWeatherUsingCoordinates(anyDouble(), anyDouble())).thenReturn(null);
        when(store.giveMeTheListOfPredictionWeatherUsingCoordinates(39.9945711, -0.071089)).thenReturn(null);

        when(weatherService.giveMeTheCurrentWeatherUsingCoordinates(anyDouble(), anyDouble())).thenReturn(null);
        when(weatherService.giveMeTheListOfPredictionsUsingCoordinates(39.9945711, -0.071089)).thenReturn(null);

        predictionWeatherUsingCoordinates.giveMeThePredictionToTheseCoordinates(new Coordinates(39.9945711, -0.071089));

        verify(store, times(1)).removeOldPredicionWeathers();
        verify(store, times(1)).giveMeTheListOfPredictionWeatherUsingCoordinates(39.9945711, -0.071089);
        verify(weatherService, times(1)).giveMeTheListOfPredictionsUsingCoordinates(39.9945711, -0.071089);
        verify(store, times(0)).addPredictionWeatherToTheDataBase(any(PredictionWeather.class));
    }
}
