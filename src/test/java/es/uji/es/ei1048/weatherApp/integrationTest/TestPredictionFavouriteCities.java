package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.PredictionWeather;
import es.uji.ei1048.weatherApp.controllerWeather.PredictionFavouriteCities;
import es.uji.ei1048.weatherApp.controllerWeather.PredictionWeatherUsingCity;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;
import es.uji.ei1048.weatherApp.exceptions.ThereAreNoFavouriteCities;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestPredictionFavouriteCities {

    PredictionFavouriteCities predictionFavouriteCities;
    //PredictionWeatherUsingCity predictionWeatherUsingCity;
    //IWeatherService weatherService;
    IStore store;

    @Before
    public void setUp() throws Exception {
        store = mock(IStore.class);
        //weatherService = mock(IWeatherService.class);
        predictionFavouriteCities = new PredictionFavouriteCities(store);
        //predictionWeatherUsingCity = new PredictionWeatherUsingCity(store, weatherService);

    }

    @After
    public void tearDown() throws Exception {
        store = null;
        //weatherService = null;
        predictionFavouriteCities = null;
        //predictionWeatherUsingCity = null;
    }

    //Hay ciudades favoritas
    @Test
    public void predictionWeatherFromFavouritesCities() throws Exception {

        List<String> listFavouritesCities = new ArrayList<>();    //Lista de favoritos

        for (int i = 0; i < 4; i++) {
            listFavouritesCities.add("Valencia");
        }

        when(store.listFavoriteCities()).thenReturn(listFavouritesCities);

        predictionFavouriteCities.giveMeThePredictionsOfMyFavoriteCities();

        verify(store, times(1)).listFavoriteCities();
    }


    // TODO: Falla este método
    //No hay ciudades favoritas
    @Test(expected = ThereAreNoFavouriteCities.class)
    public void predictionWeatherFromInexistentFavouritesCities()  {

        when(store.listFavoriteCities()).thenReturn(null);  //Debería funcionar

        predictionFavouriteCities.giveMeThePredictionsOfMyFavoriteCities();

        verify(store, times(1)).listFavoriteCities();
    }

 /*
    //Hay datos en la BBDD
    @Test
    public void validPredictionWeatherConsultationUsingAFavouriteCityWithDataInTheDB() {

        List<String> listFavouritesCities = new ArrayList<>();    //Lista de favoritos



       Map<String, List<PredictionWeather>> predictionWeatherListFromFavourites = new HashMap<>();  //Mapa con predicciones de los favoritos

        for (int j = 0; j < 4; j++) {
            listFavouritesCities.add(anyString());
            ArrayList predictionWeatherList = new ArrayList<PredictionWeather>();
            for (int i = 0; i < 3; i++) {
                predictionWeatherList.add(new PredictionWeather());
            }
            predictionWeatherListFromFavourites.put(anyString(), predictionWeatherList);
        }


        when(store.listFavoriteCities()).thenReturn(listFavouritesCities);


        Map<String, List<PredictionWeather>> predictionWeatherListFromFavourites =
        predictionFavouriteCities.giveMeThePredictionsOfMyFavoriteCities();


        verify(predictionFavouriteCities, times(1)).giveMeThePredictionsOfMyFavoriteCities();
        verify(store, times(1)).listFavoriteCities();
        //Lo hará 4 veces que es
        verify(predictionWeatherUsingCity, times(4)).giveMeThePredictionToThisCity(anyString());

        //Comprueba que efectivamente hay 4 favoritos
        Assert.assertEquals(predictionWeatherListFromFavourites.size(), 4);

    }

*/

}
