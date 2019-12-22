package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.CurrentWeather;
import es.uji.ei1048.weatherApp.controllerWeather.CurrentWeatherUsingCity;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;


public class TestCurrentWeatherCity {
        CurrentWeatherUsingCity currentWeatherUsingCity;
        IStore store;
        IWeatherService weatherService;

        @Before
        public void setUp() throws Exception {
                weatherService = mock(IWeatherService.class);
                store = mock(IStore.class);
                currentWeatherUsingCity = new CurrentWeatherUsingCity(store, weatherService);
        }

        @After
        public void tearDown() throws Exception {
                weatherService = null;
                store = null;
                currentWeatherUsingCity = null;
        }

        //Hay datos en la BBDD
        @Test
        public void validCurrentWeatherConsultationUsingACityWithDataInTheBBDD() {
        CurrentWeather currentWeather = new CurrentWeather();
                //when(store.giveMeTheCurrentWeather(anyString())).thenThrow(SQLException.class);
                when(store.giveMeTheCurrentWeather(anyString())).thenReturn(currentWeather);
                when(store.giveMeTheCurrentWeather("Castellón de la Plana")).thenReturn(currentWeather);

                currentWeatherUsingCity.giveMeTheCurrentWeatherUsingACity("Castellón de la Plana");

                verify(store, times(1)).removeOldCurrentWeathers();
                verify(store, times(1)).giveMeTheCurrentWeather("Castellón de la Plana");
                verify(weatherService, times(0)).giveMeTheCurrentWeatherUsingACity("Castellón de la Plana");
                verify(store, times(0)).addCurrentWeatherToTheDataBase(any(CurrentWeather.class));

        }

        //La ciudad no está en la BBDD con conexión
        @Test
        public void validCurrentWeatherConsultationUsingACityWithoutDataInTheBBDDWithConnection() {
                CurrentWeather currentWeather = new CurrentWeather();
                when(store.giveMeTheCurrentWeather(anyString())).thenReturn(null);
                when(weatherService.giveMeTheCurrentWeatherUsingACity(anyString())).thenReturn(null);
                when(weatherService.giveMeTheCurrentWeatherUsingACity("Castellón de la Plana")).thenReturn(currentWeather);

                currentWeatherUsingCity.giveMeTheCurrentWeatherUsingACity("Castellón de la Plana");

                verify(store, times(1)).removeOldCurrentWeathers();
                verify(store, times(1)).giveMeTheCurrentWeather("Castellón de la Plana");
                verify(weatherService, times(1)).giveMeTheCurrentWeatherUsingACity("Castellón de la Plana");
                verify(store, times(1)).addCurrentWeatherToTheDataBase(any(CurrentWeather.class));
        }

        //La ciudad no está en la BBDD SIN conexión
        //TODO Mirar las excepciones
        @Test(expected = NotValidCityException.class)
        public void validCurrentWeatherConsultationUsingACityWithoutDataInTheBBDDWithoutConnection() {
                CurrentWeather currentWeather = new CurrentWeather();
                when(store.giveMeTheCurrentWeather(anyString())).thenReturn(null);
                when(weatherService.giveMeTheCurrentWeatherUsingACity(anyString())).thenReturn(null);
                when(weatherService.giveMeTheCurrentWeatherUsingACity("Castellón de la Plana")).thenReturn(null);

                currentWeatherUsingCity.giveMeTheCurrentWeatherUsingACity("Castellón de la Plana");

                verify(store, times(1)).removeOldCurrentWeathers();
                verify(store, times(1)).giveMeTheCurrentWeather("Castellón de la Plana");
                verify(weatherService, times(1)).giveMeTheCurrentWeatherUsingACity("Castellón de la Plana");
                verify(store, times(0)).addCurrentWeatherToTheDataBase(any(CurrentWeather.class));
        }

}
