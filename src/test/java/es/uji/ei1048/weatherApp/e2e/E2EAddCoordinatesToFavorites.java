package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.model.Coordinates;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class E2EAddCoordinatesToFavorites extends E2ETestBed {

    public E2EAddCoordinatesToFavorites(){
        super();
    }

    @Test
    public void addValidCoordinatesToFavorites(){
        //Given: coordenadas válidas
        double lon =  39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);

        //TODO es correcto poner esto aquí?
        //nos aseguramos de que las coordenadas que no estén las coordenadas necesarias en la BBDD para hacer el test
        weatherAppFacade.deleteCoordinatesFromFavorites(coordinates);

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

        //nos aseguramos de que las coordenadas necesarias estén en la BBDD para hacer el test
        weatherAppFacade.addCoordinatesToFavorites(coordinates);


        //When : intento añadir unas coordenadas ya presentes en favoritos
        boolean added = weatherAppFacade.addCoordinatesToFavorites(coordinates);


        //Then no se añaden a favoritos
        assertFalse(added);

    }





}
