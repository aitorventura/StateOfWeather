package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.model.Coordinates;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class E2EDeleteCoordinatesFromFavorites  extends  E2ETestBed{

    public E2EDeleteCoordinatesFromFavorites(){
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        double lon =  39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);
        weatherAppFacade.addCoordinatesToFavorites(coordinates);

    }

    @Test
    public void deleteExistentCoordinatesFromFavorites(){
        //Given: coordenadas presentes en favoritos
        double lon =  39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);

        //When: queremos borrar unas coordenadas presentes en favoritos
        boolean deleted = weatherAppFacade.deleteCoordinatesFromFavorites(coordinates);


        //Then: las borramos de favoritos
        assertTrue(deleted);

    }

    @Test
    public void deleteNonexistentCoordenatesInFavorites(){
        //Given: coordenadas que no existen en favoritos
        double lon = -500.908;
        double lat = 56.872;
        Coordinates coordinates = new Coordinates(lon, lat);

        //When: no existe la ciudad que quiero borrar en favoritos
        boolean deleted = weatherAppFacade.deleteCoordinatesFromFavorites(coordinates);


        //Then
        assertFalse(deleted);

    }
}
