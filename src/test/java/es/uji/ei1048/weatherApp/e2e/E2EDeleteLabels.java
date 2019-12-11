package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.Coordinates;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class E2EDeleteLabels  extends  E2ETestBed {


    public E2EDeleteLabels(){
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        double lon =  39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);
        String newLabel = "UJI";
        weatherAppFacade.addLabel(newLabel, coordinates);

    }


    @Test
    public void deleteValidLabelFromCoordinates(){
        //Given: etiqueta válida
        String nameOfLabel = "UJI";

        //When: borro la etiqueta válida y que quiero borrar
        boolean result = weatherAppFacade.deleteLabel(nameOfLabel);

        //Then
        assertTrue(result);

    }

    @Test
    public void deleteInvalidLabelFromCoordinates(){
        //Given: etiqueta inválida
        String nameOfLabel = "ñññ";


        //When: no existe la etiqueta que quiero borrar
        boolean result = weatherAppFacade.deleteLabel(nameOfLabel);


        //Then
        assertFalse(result);

    }

}
