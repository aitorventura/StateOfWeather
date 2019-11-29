package es.uji.ei1048.weatherApp.e2e;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class E2EDeleteCityFromFavorites extends  E2ETestBed {


    public E2EDeleteCityFromFavorites(){
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        weatherAppFacade.addCityToFavorites("Valencia");

    }


    @Test
    public void deleteExistentCityOfFavorites(){
        //Given: ciudad presente en favoritos
        String city = "Valencia";

        //When: queremos borrar una ciudad presente en favoritos
        boolean deleted = weatherAppFacade.deleteCityFromFavorites(city);


        //Then: la borramos de favoritos
        assertTrue(deleted);

    }

    @Test
    public void deleteInexistentCityOfFavorites(){
        //Given: ciudad no añadida en favoritos
        String city = "ñññ";


        //When: no existe la ciudad que quiero borrar en favoritos
        boolean deleted = weatherAppFacade.deleteCityFromFavorites(city);


        //Then
        assertFalse(deleted);

    }

}
