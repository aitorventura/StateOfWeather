package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.PredictionWeather;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class E2EPredictionListFromFavouriteWithConnection extends  E2ETestBed {


    @Test
    public void predictionOfFavouritePlacesWithConnectionAndDataLessThan1Hour(){

        //Given: lista de favoritos
        //TODO en principio no se necesita nada


        //When: entro en la aplicación, hay conexión y datos de menos de XXX
        //TODO: Método que devuelva el tiempo de los datos


        //Then: se muestran los datos

    }


    @Test
    public void predictionOfFavouritePlacesWithConnectionAndWithoutDataLessThan1Hour(){

        //Given: lista de favoritos
        //TODO en principio no se necesita nada


        //When: entro en la aplicación, hay conexión, pero no datos de menos de XXX en la BBDD
        Map<String, List<PredictionWeather>> predictionOfFavouriteCities = weatherAppFacade.predictionOfFavouriteCities();
        Map<Coordinates, List<PredictionWeather>> predictionOfFavouriteCoordinates = weatherAppFacade.predictionOfFavouriteCoordenates();

        //Then: se muestran los datos

    }




}
