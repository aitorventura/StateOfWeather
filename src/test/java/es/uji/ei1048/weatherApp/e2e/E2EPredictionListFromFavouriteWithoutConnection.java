package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.exceptions.NoDataInTheDatabaseAndOffline;
import org.junit.Test;

import java.util.List;

public class E2EPredictionListFromFavouriteWithoutConnection extends  E2ETestBed {


    @Test
    public void predictionOfFavouritePlacesWithoutConnectionAndDataLessThan3Days(){

        //Given: lista de favoritos


        //TODO se supone que si no hay conexión, la info se quedará en la bbdd un día, eso no se comprueba


        //When: entro en la aplicación, hay conexión y datos de menos de XXX
        //TODO: Método que devuelva el tiempo de los datos  -> int <= 3 días
        //TODO: Obtengo las predicciones de la BBDD

        //Then: se muestran los datos

    }


    @Test(expected = NoDataInTheDatabaseAndOffline.class)
    public void predictionOfFavouritePlacesWithoutConnectionAndWithoutDataLessThan3Days(){

        //Given: lista de favoritos


        //When: entro en la aplicación, hay conexión, pero no datos de menos de XXX en la BBDD
        //TODO: Método que devuelva el tiempo de los datos
        //TODO: Calcular predicciones


        //Then: Error

    }




}
