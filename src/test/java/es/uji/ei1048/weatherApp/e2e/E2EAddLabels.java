package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class E2EAddLabels extends E2ETestBed {

    @Test
    public void addValidCoordinates(){
        //Given: coordenadas válidas
        double lon =  39.9945711;
        double lat = -0.071089;
        String newLabel = "UJI";
        Coordinates coordinates = new Coordinates(lon, lat);


        //When : añado una nueva etiqueta
        boolean result = weatherAppFacade.addLabel(newLabel, coordinates);

        //Then
        assertTrue(result);

    }

    @Test
    public void addInvalidCoordinates(){
        //Given: coordenadas válidas
        double lon =  395.9733;
        double lat = -8.002;
        String newLabel = "Casa";
        Coordinates coordinates = new Coordinates(lon, lat);



        //When : intento añadir una nueva etiqueta
        boolean result = weatherAppFacade.addLabel(newLabel, coordinates);


        //Then: espero que devuelve false
        assertFalse(result);


    }


}
