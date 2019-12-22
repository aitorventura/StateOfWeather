package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.controllerWeather.SavedLabels;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TestLabels {

    SavedLabels savedLabels;
    IStore store;

    @Before
    public void setUp() throws Exception {
        store = mock(IStore.class);
        savedLabels = new SavedLabels(store);
    }

    @After
    public void tearDown() throws Exception {
        store = null;
        savedLabels = null;
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
     
        boolean result = savedLabels.addLabel("UJI", coordinates);
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
}
