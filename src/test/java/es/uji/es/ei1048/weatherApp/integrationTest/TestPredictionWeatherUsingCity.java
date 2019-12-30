package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.model.PredictionWeather;

import es.uji.ei1048.weatherApp.controllerWeather.PredictionWeatherUsingCity;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestPredictionWeatherUsingCity {

    PredictionWeatherUsingCity predictionWeatherUsingCity;
    IStore store;
    IWeatherService weatherService;

    @Before
    public void setUp() throws Exception {
        weatherService = mock(IWeatherService.class);
        store = mock(IStore.class);
        predictionWeatherUsingCity = new PredictionWeatherUsingCity(store, weatherService);
    }

    @After
    public void tearDown() throws Exception {
        weatherService = null;
        store = null;
        predictionWeatherUsingCity = null;
    }

    //Hay datos en la BBDD
    @Test
    public void validPredictionWeatherConsultationUsingACityWithDataInTheDB() {
        ArrayList predictionWeatherList = new ArrayList<PredictionWeather>();
        for (int i = 0; i < 3; i++) {
            predictionWeatherList.add(new PredictionWeather());
        }
        //when(store.giveMeTheCurrentWeather(anyString())).thenThrow(SQLException.class);
        when(store.giveMeTheListOfPredictionWeather("Castellón de la Plana")).thenReturn(predictionWeatherList);

        predictionWeatherUsingCity.giveMeThePredictionToThisCity("Castellón de la Plana");

        verify(store, times(1)).removeOldPredicionWeathers();
        verify(store, times(1)).giveMeTheListOfPredictionWeather("Castellón de la Plana");
        verify(weatherService, times(0)).giveMeTheListOfPredictionsUsingACity("Castellón de la Plana");
        verify(store, times(0)).addPredictionWeatherToTheDataBase(any(PredictionWeather.class));

    }

    //La ciudad no está en la BBDD con conexión
    @Test
    public void validPredictionWeatherConsultationUsingACityWithoutDataInTheBBDDWithConnection() {
        ArrayList <PredictionWeather> predictionWeatherList = new ArrayList<PredictionWeather>();
        for (int i = 0; i < 3; i++) {
            predictionWeatherList.add(new PredictionWeather());
        }
        //when(store.giveMeTheCurrentWeather(anyString())).thenThrow(SQLException.class);
        when(store.giveMeTheListOfPredictionWeather("Castellón de la Plana")).thenReturn(null);

        when(weatherService.giveMeTheListOfPredictionsUsingACity(anyString())).thenReturn(null);
        when(weatherService.giveMeTheListOfPredictionsUsingACity("Castellón de la Plana")).thenReturn(predictionWeatherList);


        predictionWeatherUsingCity.giveMeThePredictionToThisCity("Castellón de la Plana");

        verify(store, times(1)).removeOldPredicionWeathers();
        verify(store, times(1)).giveMeTheListOfPredictionWeather("Castellón de la Plana");
        verify(weatherService, times(1)).giveMeTheListOfPredictionsUsingACity("Castellón de la Plana");
        verify(store, times(3)).addPredictionWeatherToTheDataBase(any(PredictionWeather.class));

    }

    //La ciudad no está en la BBDD SIN conexión
    //TODO Mirar las excepciones
    @Test(expected = NotValidCityException.class)
    public void validPredictionWeatherConsultationUsingACityWithoutDataInTheBBDDWithoutConnection() {
        ArrayList predictionWeatherList = new ArrayList<PredictionWeather>();
        for (int i = 0; i < 3; i++) {
            predictionWeatherList.add(new PredictionWeather());
        }
        //when(store.giveMeTheCurrentWeather(anyString())).thenThrow(SQLException.class);
        when(store.giveMeTheListOfPredictionWeather(anyString())).thenReturn(null);
        when(store.giveMeTheListOfPredictionWeather("Castellón de la Plana")).thenReturn(null);

        when(weatherService.giveMeTheListOfPredictionsUsingACity(anyString())).thenReturn(null);
        when(weatherService.giveMeTheListOfPredictionsUsingACity("Castellón de la Plana")).thenReturn(null);


        predictionWeatherUsingCity.giveMeThePredictionToThisCity("Castellón de la Plana");

        verify(store, times(1)).removeOldPredicionWeathers();
        verify(store, times(1)).giveMeTheListOfPredictionWeather("Castellón de la Plana");
        verify(weatherService, times(1)).giveMeTheListOfPredictionsUsingACity("Castellón de la Plana");
        verify(store, times(0)).addPredictionWeatherToTheDataBase(any(PredictionWeather.class));

    }


}
