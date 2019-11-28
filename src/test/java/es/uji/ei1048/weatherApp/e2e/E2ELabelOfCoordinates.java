package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.Label;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class E2ELabelOfCoordinates extends E2ETestBed {

    @Test
    public void addValidCoordinates(){
        //Given: coordenadas válidas
        double lon =  39.9945711;
        double lat = -0.071089;
        String newLabel = "UJI";


        //When : añado una nueva etiqueta
        Label label = new Label(lon, lat, newLabel);


        //Then
        assertTrue(label.getLabel(lon, lat).equals("UJI"));

    }

    @Test
    public void addInvalidCoordinates(){
        //Given: coordenadas válidas
        double lon =  395.9733;
        double lat = -8.002;
        String newLabel = "Casa";


        //When : intento añadir una nueva etiqueta
        Label label = new Label(lon, lat, newLabel);


        //Then: espero que se lanze una excepción

    }

    @Test
    public void deleteValidLabelFromCoordinates(){
        //Given: coordenadas válidas
        String nameOfLabel = "UJI";


        //When: existe la etiqueta que quiero borrar
        Boolean exists = weatherAppFacade.containsLabel(nameOfLabel);


        //Then
        assertTrue(exists ==  true);
        assertTrue(weatherAppFacade.deleteLabel(nameOfLabel) !=  null);

    }

    @Test
    public void deleteInvalidLabelFromCoordinates(){
        //Given: coordenadas válidas
        String nameOfLabel = "ñññ";


        //When: no existe la etiqueta que quiero borrar
        Boolean exists = weatherAppFacade.containsLabel(nameOfLabel);   //Devuelve si existe -> TRUE


        //Then
        assertTrue(exists ==  false);
        assertTrue(weatherAppFacade.deleteLabel(nameOfLabel) ==  null);   //Si no existía devuelve null
    }


}
