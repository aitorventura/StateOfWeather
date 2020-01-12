package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.controllerWeather.PredictionFavouriteCities;
import es.uji.ei1048.weatherApp.exceptions.ThereAreNoFavouriteCities;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestPredictionFavouriteCities {

    PredictionFavouriteCities predictionFavouriteCities;
    IStore store;

    @Before
    public void setUp() throws Exception {
        store = mock(IStore.class);
        predictionFavouriteCities = new PredictionFavouriteCities(store);
    }

    @After
    public void tearDown() throws Exception {
        store = null;
        predictionFavouriteCities = null;
    }

    //Hay ciudades favoritas
    @Test
    public void predictionWeatherFromFavouritesCities() throws Exception {
        List<String> listFavouritesCities = new ArrayList<>();    //Lista de favoritos

        for (int i = 0; i < 4; i++) {
            listFavouritesCities.add("Valencia");
        }

        when(store.listFavoriteCities()).thenReturn(listFavouritesCities);

        predictionFavouriteCities.giveMeThePredictionsOfMyFavoriteCities();

        verify(store, times(1)).listFavoriteCities();
    }

    //No hay ciudades favoritas
    @Test(expected = ThereAreNoFavouriteCities.class)
    public void predictionWeatherFromInexistentFavouritesCities()  {
        List<String> listFavouriteCities = new ArrayList<>();
        when(store.listFavoriteCities()).thenReturn(listFavouriteCities);
        predictionFavouriteCities.giveMeThePredictionsOfMyFavoriteCities();

        verify(store, times(1)).listFavoriteCities();
    }
}
