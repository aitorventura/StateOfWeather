package es.uji.ei1048.weatherApp.e2e;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class E2EPredictionListFromFavouriteWithConnection extends  E2ETestBed {


    @Test
    public void predictionOfFavouritePlacesWithConnectionAndDataLessThan1Hour(){

        //Given: lista de favoritos
        List<String> favouriteCities =  weatherAppFacade.getListOfFauvoriteCities();
        List<Double[]> favouriteCoordinates =  weatherAppFacade.getListOfFauvoriteCoordinates();


        //When: entro en la aplicación, hay conexión y datos de menos de XXX
        //TODO: Método que devuelva el tiempo de los datos


        //Then: se muestran los datos

    }


    @Test
    public void predictionOfFavouritePlacesWithConnectionAndWithoutDataLessThan1Hour(){

        //Given: lista de favoritos
        List<String> favouriteCities =  weatherAppFacade.getListOfFauvoriteCities();
        List<Double[]> favouriteCoordinates =  weatherAppFacade.getListOfFauvoriteCoordinates();


        //When: entro en la aplicación, hay conexión, pero no datos de menos de XXX en la BBDD
        //TODO: Método que devuelva el tiempo de los datos
        //TODO: Calcular predicciones


        //Then: se muestran los datos

    }




}
