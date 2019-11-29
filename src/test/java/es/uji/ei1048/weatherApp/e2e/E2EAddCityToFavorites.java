package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.WeatherAppFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class E2EAddCityToFavorites extends E2ETestBed {

    public E2EAddCityToFavorites(){
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        weatherAppFacade.addCityToFavorites("Castellón de la Plana");

    }


    @Test
    public void addValidCityToFavorites(){
        //Given: ciudad válida
        String city = "Valencia";

        //When: añado una ciudad que existe a favoritos
        boolean added = weatherAppFacade.addCityToFavorites(city);


        //Then se añade correctamente a favoritos
        assertTrue(added);

    }

    @Test
    public void addExistentCityToFavorites(){
        //Given: ciudad ya añadida en favoritos
        String city = "Castellón de la Plana";

        //When : intento añadir una ciudad ya presente en favoritos
        boolean added = weatherAppFacade.addCityToFavorites(city);


        //Then no se añade a favoritos
        assertFalse(added);

    }

}
