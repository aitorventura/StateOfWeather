package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.controllerWeather.SavedLabels;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TestLabels {

    SavedLabels savedLabels;
    IStore store;
    IWeatherService weatherService;

    double lon;
    double lat;
    Coordinates coordinates;
    CurrentWeather currentWeather;

    @Before
    public void setUp() throws Exception {
        store = mock(IStore.class);
        weatherService = mock(IWeatherService.class);
        savedLabels = new SavedLabels(store, weatherService);

        lon = 39.9945711;
        lat = -0.071089;
        coordinates = new Coordinates(lon, lat);

        currentWeather = new CurrentWeather();
    }

    @After
    public void tearDown() throws Exception {
        store = null;
        weatherService = null;
        savedLabels = null;
        coordinates = null;
        currentWeather = null;
    }

    //Añadir una nueva etiqueta que no está en la BBDD con coordenadas válidas
    @Test
    public void addValidLabel() {

        Coordinates coordinates = new Coordinates(39.9945711, -0.071089);
        when(store.addLabel("UJI", coordinates)).thenReturn(true);

        boolean result = savedLabels.addLabel("UJI",coordinates);
        verify(store, times(1)).addLabel("UJI",coordinates);

        Assert.assertTrue(result);
    }

    //Añadir una nueva etiqueta que ya existe en la BBDD con coordenadas válidas
    @Test
    public void addInvalidLabel() {
        Coordinates coordinates = new Coordinates(39.9945711, -0.071089);
        when(store.addLabel("UJI", coordinates)).thenReturn(false);

        boolean result = savedLabels.addLabel("UJI",coordinates);
        verify(store, times(1)).addLabel("UJI",coordinates);

        Assert.assertFalse(result);
    }

    //Añadir una etiqueta con coordenadas inválidas
    @Test(expected = NotValidCoordinatesException.class)
    public void addLabelWithInvalidCoordinates() {
        Coordinates coordinates = new Coordinates(-500, 58821);
        when(store.addLabel(anyString(), any(Coordinates.class))).thenReturn(false);

        boolean result = savedLabels.addLabel("UJI", coordinates);

        verify(savedLabels, times(1)).addLabel("UJI", coordinates);
        verify(store, times(0)).addLabel(anyString(), coordinates);

        Assert.assertFalse(result);
    }

    //Borrar una etiqueta que existe en la BBDD
    @Test
    public void removeValidLabel() {

        when(store.removeLabel("UJI")).thenReturn(true);

        boolean result = savedLabels.deleteLabel("UJI");
        verify(store, times(1)).removeLabel("UJI");

        Assert.assertTrue(result);
    }

    //Borrar una etiqueta que no existe en la BBDD
    @Test
    public void removeInvalidLabel() {

        when(store.removeLabel(anyString())).thenReturn(false);

        boolean result = savedLabels.deleteLabel(anyString());
        verify(store, times(1)).removeLabel(anyString());

        Assert.assertFalse(result);
    }

    //Buscar una etiqueta que NO existe en la BBDD
    @Test
    public void getInvalidLabel() {

        when(store.getCoordinatesOfLabel(anyString())).thenReturn(null);

        savedLabels.getCurrentWeatherOfLabel(anyString());

        verify(store, times(1)).getCoordinatesOfLabel(anyString());
        verify(store, times(0)).giveMeTheCurrentWeather(anyDouble(),anyDouble());
        verify(weatherService, times(0)).giveMeTheCurrentWeatherUsingCoordinates(anyDouble(),anyDouble());

    }

    //Buscar una etiqueta que existe en la BBDD y el tiempo actual también está en la BBDD
    @Test
    public void getValidLabelWithCurrentWeatherInDB() {

        /*double lon = 39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);
        CurrentWeather currentWeather = new CurrentWeather();*/

        when(store.getCoordinatesOfLabel("UJI")).thenReturn(coordinates);
        when(store.giveMeTheCurrentWeather(lon,lat)).thenReturn(currentWeather);

        savedLabels.getCurrentWeatherOfLabel("UJI");

        verify(store, times(1)).getCoordinatesOfLabel("UJI");
        verify(store, times(1)).giveMeTheCurrentWeather(lon,lat);
        verify(weatherService, times(0)).giveMeTheCurrentWeatherUsingCoordinates(anyDouble(),anyDouble());

    }

    //Buscar una etiqueta que existe en la BBDD y el tiempo actual NO está en la BBDD (CON conexión)
    @Test
    public void getValidLabelWithoutCurrentWeatherInDBWithConnection() {

        /*double lon = 39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);
        CurrentWeather currentWeather = new CurrentWeather();*/

        when(store.getCoordinatesOfLabel("UJI")).thenReturn(coordinates);
        when(store.giveMeTheCurrentWeather(lon,lat)).thenReturn(null);
        when(weatherService.giveMeTheCurrentWeatherUsingCoordinates(lon,lat)).thenReturn(currentWeather);

        savedLabels.getCurrentWeatherOfLabel("UJI");

        verify(store, times(1)).getCoordinatesOfLabel("UJI");
        verify(store, times(1)).giveMeTheCurrentWeather(lon,lat);
        verify(weatherService, times(1)).giveMeTheCurrentWeatherUsingCoordinates(lon,lat);
    }

    //TODO si se lanzara una excepción por no tener conexión si que haría falta este método
    //Buscar una etiqueta que existe en la BBDD y el tiempo actual NO está en la BBDD (SIN conexión)
  /*  @Test
    public void getValidLabelWithoutCurrentWeatherInDBWithoutConnection() {

        when(store.getCoordinatesOfLabel("UJI")).thenReturn(coordinates);
        when(store.giveMeTheCurrentWeather(lon,lat)).thenReturn(null);
        when(weatherService.giveMeTheCurrentWeatherUsingCoordinates(lon,lat)).thenReturn(null);

        savedLabels.getCurrentWeatherOfLabel("UJI");

        verify(store, times(1)).getCoordinatesOfLabel("UJI");
        verify(store, times(1)).giveMeTheCurrentWeather(lon,lat);
        verify(weatherService, times(1)).giveMeTheCurrentWeatherUsingCoordinates(lon,lat);
    }
*/
}
