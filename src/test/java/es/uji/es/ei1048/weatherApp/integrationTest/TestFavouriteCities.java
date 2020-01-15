package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.controllerWeather.FavouriteCities;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TestFavouriteCities {

    FavouriteCities favouriteCities;
    IStore store;
    IWeatherService weatherService;

    @Before
    public void setUp() throws Exception {
        weatherService = mock(IWeatherService.class);
        store = mock(IStore.class);
        favouriteCities = new FavouriteCities(store, weatherService);
    }

    @After
    public void tearDown() throws Exception {
        weatherService = null;
        store = null;
        favouriteCities = null;
    }

    @Test
    public void addCityWhichNotExists() {
        when(weatherService.giveMeTheCurrentWeatherUsingACity("sds<zdsxcsd")).thenReturn(null);

        boolean result = favouriteCities.addCityToFavourite("sds<zdsxcsd");

        verify(weatherService, times(1)).giveMeTheCurrentWeatherUsingACity("sds<zdsxcsd");

        verify(store, times(0)).addCityToFavorite("sds<zdsxcsd");

        Assert.assertFalse(result);
    }

    @Test
    public void addCityWhichExistsAndIsntInTheDB() {
        CurrentWeather c = new CurrentWeather();
        c.setCity("Castellón de la Plana");

        when(weatherService.giveMeTheCurrentWeatherUsingACity("Castellón de la Plana")).thenReturn(c);
        when(store.addCityToFavorite("Castellón de la Plana")).thenReturn(true);

        boolean result = favouriteCities.addCityToFavourite("Castellón de la Plana");

        verify(weatherService, times(1)).giveMeTheCurrentWeatherUsingACity("Castellón de la Plana");

        verify(store, times(1)).addCityToFavorite(c.getCity());

        Assert.assertTrue(result);
    }

    @Test
    public void addCityWhichExistsAndIsInTheDB() {
        CurrentWeather c = new CurrentWeather();
        c.setCity("Castellón de la Plana");
        when(weatherService.giveMeTheCurrentWeatherUsingACity("Castellón de la Plana")).thenReturn(c);
        when(store.addCityToFavorite("Castellón de la Plana")).thenReturn(false);

        boolean result = favouriteCities.addCityToFavourite("Castellón de la Plana");

        verify(weatherService, times(1)).giveMeTheCurrentWeatherUsingACity("Castellón de la Plana");

        verify(store, times(1)).addCityToFavorite(c.getCity());

        Assert.assertFalse(result);
    }


    @Test
    public void removeCityWhichIsInTheDB() {
        when(store.removeCityFromFavorite(anyString())).thenReturn(false);
        when(store.removeCityFromFavorite("Castellón de la Plana")).thenReturn(true);

        boolean result = favouriteCities.removeCityFromFavourite("Castellón de la Plana");

        verify(store, times(1)).removeCityFromFavorite("Castellón de la Plana");

        Assert.assertTrue(result);
    }

    @Test
    public void removeCityWhichIsntInTheDB() {
        when(store.removeCityFromFavorite(anyString())).thenReturn(true);
        when(store.removeCityFromFavorite("Castellón de la Plana")).thenReturn(false);

        boolean result = favouriteCities.removeCityFromFavourite("Castellón de la Plana");

        verify(store, times(1)).removeCityFromFavorite("Castellón de la Plana");

        Assert.assertFalse(result);
    }
}