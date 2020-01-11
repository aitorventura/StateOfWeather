package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.model.Coordinates;
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
        double lat =  39.9945711;
        double lon = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);
        String newLabel = "UJI";

        //nos aseguramos de que la etiqueta está en Labels
        weatherAppFacade.addLabel(newLabel, coordinates);

    }


    @Test
    public void deleteValidLabelFromCoordinates(){
        //Given: etiqueta válida
        String label = "UJI";

        //When: borro la etiqueta válida y que quiero borrar
        boolean result = weatherAppFacade.deleteLabel(label);

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
