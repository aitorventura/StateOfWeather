package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.controllerWeather.PredictionFavouriteCoordinates;
import es.uji.ei1048.weatherApp.exceptions.ThereAreNoFavouriteCoordinates;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestPredictionFavouriteCoordinates {

    PredictionFavouriteCoordinates predictionFavouriteCoordinates;
    IStore store;

    @Before
    public void setUp() throws Exception {
        store = mock(IStore.class);
        predictionFavouriteCoordinates = new PredictionFavouriteCoordinates(store);

    }

    @After
    public void tearDown() throws Exception {
        store = null;
        predictionFavouriteCoordinates = null;
    }

    //Hay coordenadas favoritas
    @Test
    public void predictionWeatherFromFavouritesCoordinates() throws Exception {

        List<Coordinates> listFavouriteCoordinates = new ArrayList<>();    //Lista de favoritos

        for (int i = 0; i < 4; i++) {
            listFavouriteCoordinates.add(new Coordinates(39.9945711, -0.071089));
        }

        when(store.listFavoriteCoordinates()).thenReturn(listFavouriteCoordinates);

        predictionFavouriteCoordinates.giveMeThePredictionsOfMyFavoriteCoordinates();

        verify(store, times(1)).listFavoriteCoordinates();
    }

    //No hay coordenadas favoritas
    @Test(expected = ThereAreNoFavouriteCoordinates.class)
    public void predictionWeatherFromInexistentFavouritesCoordinates()  {
        ArrayList<Coordinates> c = new ArrayList<>();
        when(store.listFavoriteCoordinates()).thenReturn(c);

        predictionFavouriteCoordinates.giveMeThePredictionsOfMyFavoriteCoordinates();

        verify(store, times(1)).listFavoriteCoordinates();
    }

}
