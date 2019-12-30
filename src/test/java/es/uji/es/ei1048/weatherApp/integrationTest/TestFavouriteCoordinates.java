package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.controllerWeather.FavouriteCoordinates;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestFavouriteCoordinates {
    FavouriteCoordinates favouriteCoordinates;
    IStore store;

    @Before
    public void setUp() throws Exception {
        store = mock(IStore.class);
        favouriteCoordinates = new FavouriteCoordinates(store);
    }

    @After
    public void tearDown() throws Exception {
        store = null;
        favouriteCoordinates = null;
    }

    @Test
    public void addCoordinatesWhichNotExists() {

        boolean result = favouriteCoordinates.addCoordinatesToFavourite(new Coordinates(-500, 58821));

        verify(store, times(0)).addCoordinatesToFavorite(new Coordinates(-500, 58821));

        Assert.assertFalse(result);


    }

    @Test
    public void addCoordinatesWhichExistAndArenotInTheDB() {

        when(store.addCoordinatesToFavorite(new Coordinates(39.9945711, -0.071089))).thenReturn(true);

        boolean result = favouriteCoordinates.addCoordinatesToFavourite(new Coordinates(39.9945711, -0.071089));

        verify(store, times(1)).addCoordinatesToFavorite(new Coordinates(39.9945711, -0.071089));

        Assert.assertTrue(result);

    }

    @Test
    public void addCoordinatesWhichExistAndAreInTheDB() {

        when(store.addCoordinatesToFavorite(new Coordinates(39.9945711, -0.071089))).thenReturn(false);

        boolean result = favouriteCoordinates.addCoordinatesToFavourite(new Coordinates(39.9945711, -0.071089));

        verify(store, times(1)).addCoordinatesToFavorite(new Coordinates(39.9945711, -0.071089));

        Assert.assertFalse(result);
    }


    @Test
    public void removeCoordinatesWhichAreInTheDB() {
        Coordinates c = new Coordinates(39.9945711, -0.071089);
        List<Coordinates> list = new ArrayList<>();

        list.add(c);

        when(store.listFavoriteCoordinates()).thenReturn(list);

        when(store.removeCoordinatesFromFavorite(any(Coordinates.class))).thenReturn(false);
        when(store.removeCoordinatesFromFavorite(c)).thenReturn(true);

        boolean result = favouriteCoordinates.removeCoordinatesFromFavourite(c);


        verify(store, times(1)).listFavoriteCoordinates();
        verify(store, times(1)).removeCoordinatesFromFavorite(c);

        Assert.assertTrue(result);
    }

    @Test
    public void removeCoordinatesWhichArentInTheDB() {
        Coordinates c = new Coordinates(39.9945711, -0.071089);
        List<Coordinates> list = new ArrayList<>();
        list.add(new Coordinates(1,1));

        when(store.listFavoriteCoordinates()).thenReturn(list);

        when(store.removeCoordinatesFromFavorite(any(Coordinates.class))).thenReturn(true);
        when(store.removeCoordinatesFromFavorite(c)).thenReturn(false);

        boolean result = favouriteCoordinates.removeCoordinatesFromFavourite(c);


        verify(store, times(1)).listFavoriteCoordinates();
        verify(store, times(0)).removeCoordinatesFromFavorite(c);

        Assert.assertFalse(result);
    }

}
