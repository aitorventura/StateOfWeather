package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.Coordinates;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class E2EAddCoordinatesToFavorites extends E2ETestBed {

    public E2EAddCoordinatesToFavorites(){
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        double lon = 39.7305972;
        double lat = -0.5330876;
        Coordinates coordinates = new Coordinates(lon, lat);
        weatherAppFacade.addCoordinatesToFavorites(coordinates);

    }


    @Test
    public void addValidCoordinatesToFavorites(){
        //Given: coordenadas válidas
        double lon =  39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);

        //When: añado unas coordenadas que existen a favoritos
        boolean added = weatherAppFacade.addCoordinatesToFavorites(coordinates);


        //Then se añaden correctamente a favoritos
        assertTrue(added);

    }

    @Test
    public void addExistentCoordinatesToFavorites(){
        //Given: coordenadas ya añadidas en favoritos
        double lon = 39.7305972;
        double lat = -0.5330876;
        Coordinates coordinates = new Coordinates(lon, lat);


        //When : intento añadir unas coordenadas ya presentes en favoritos
        boolean added = weatherAppFacade.addCoordinatesToFavorites(coordinates);


        //Then no se añaden a favoritos
        assertFalse(added);

    }





}
