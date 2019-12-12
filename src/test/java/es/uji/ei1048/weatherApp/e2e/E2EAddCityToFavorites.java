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

    @Test
    public void addValidCityToFavorites(){

        //nos aseguramos de que la ciudad no esté en favoritos
        weatherAppFacade.deleteCityFromFavorites("Valencia");

        //Given: ciudad válida
        String city = "Valencia";

        //When: añado una ciudad que existe a favoritos
        boolean added = weatherAppFacade.addCityToFavorites(city);

        //Then se añade correctamente a favoritos
        assertTrue(added);

    }

    @Test
    public void addExistentCityToFavorites(){

        //nos aseguramos de que ya está la ciudad en favoritos
        weatherAppFacade.addCityToFavorites("Castellón de la Plana");

        //Given: ciudad ya añadida en favoritos
        String city = "Castellón de la Plana";

        //When : intento añadir una ciudad ya presente en favoritos
        boolean added = weatherAppFacade.addCityToFavorites(city);

        //Then no se añade a favoritos
        assertFalse(added);

    }


    @Test
    public void addFakeCityToFavorites(){

        //Given: ciudad no válida en favoritos
        String city = "asñdksañ";

        //When : intento añadir una ciudad que no existe en favoritos
        boolean added = weatherAppFacade.addCityToFavorites(city);

        //Then no se añade a favoritos
        assertFalse(added);

    }
}
