package es.uji.ei1048.weatherApp.e2e;

import com.sun.org.apache.xpath.internal.operations.Bool;
import es.uji.ei1048.weatherApp.Label;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class E2EFCityToFavorites extends E2ETestBed {


    @Test
    public void addValidCityToFavorites(){
        //Given: ciudad válidas
        String city = "Valencia";

        //When: añado una ciudad que existe a favoritos
        Boolean added = weatherAppFacade.addCityToFavorites(city);


        //Then se añade correctamente a favoritos
        assertTrue(added == true);

    }

    @Test
    public void addExistentCityToFavorites(){
        //Given: ciudad ya añadida en favoritos
        String city = "Valencia";

        //When : intento añadir una ciudad ya presente en favoritos
        Boolean added = weatherAppFacade.addCityToFavorites(city);


        //Then no se añade a favoritos
        assertTrue(added == false);

    }

    @Test
    public void deleteExistentCityOfFavorites(){
        //Given: ciudad presente en favoritos
        String city = "Valencia";


        //When: queremos borrar una ciudad presente en favoritos
        Boolean deleted = weatherAppFacade.deleteCityToFavorites(city);


        //Then: la borramos de favoritos
        assertTrue(deleted ==  true);

    }

    @Test
    public void deleteInexistentCityOfFavorites(){
        //Given: ciudad no añadida en favoritos
        String city = "ñññ";


        //When: no existe la ciudad que quiero borrar en favoritos
        Boolean deleted = weatherAppFacade.deleteCityToFavorites(city);


        //Then
        assertTrue(deleted ==  false);

    }



}
