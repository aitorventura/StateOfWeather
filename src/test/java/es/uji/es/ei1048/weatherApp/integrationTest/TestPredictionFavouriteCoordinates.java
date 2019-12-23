package es.uji.es.ei1048.weatherApp.integrationTest;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.controllerWeather.PredictionFavouriteCities;
import es.uji.ei1048.weatherApp.controllerWeather.PredictionFavouriteCoordinates;
import es.uji.ei1048.weatherApp.exceptions.ThereAreNoFavouriteCities;
import es.uji.ei1048.weatherApp.exceptions.ThereAreNoFavouriteCoordinates;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestPredictionFavouriteCoordinates {

    PredictionFavouriteCoordinates predictionFavouriteCoordinates;
    IStore store;

    @Before
    public void setUp() throws Exception {
        store = mock(IStore.class);
        predictionFavouriteCoordinates = new PredictionFavouriteCoordinates(store);

    }

    @After
    public void tearDown() throws Exception {
        store = null;
        predictionFavouriteCoordinates = null;
    }

    //Hay coordenadas favoritas
    @Test
    public void predictionWeatherFromFavouritesCoordinates() throws Exception {

        List<Coordinates> listFavouriteCoordinates = new ArrayList<>();    //Lista de favoritos

        for (int i = 0; i < 4; i++) {
            listFavouriteCoordinates.add(new Coordinates(39.9945711, -0.071089));
        }

        when(store.listFavoriteCoordinates()).thenReturn(listFavouriteCoordinates);

        predictionFavouriteCoordinates.giveMeThePredictionsOfMyFavoriteCoordinates();

        verify(store, times(1)).listFavoriteCoordinates();
    }


    // TODO: Si no pongo expect = ThereAreNoFavouriteCities, funciona, si lo pongo no. No entiendo ir a
    //  PredictionFavouriteCities y mirar el to-do que he puesto @Zayda
    //No hay coordenadas favoritas
    @Test(expected = ThereAreNoFavouriteCoordinates.class)
    public void predictionWeatherFromInexistentFavouritesCoordinates()  {
        ArrayList<Coordinates> c = new ArrayList<>();
        when(store.listFavoriteCoordinates()).thenReturn(c);  //TODO: Es como si esto no lo hiciera

        predictionFavouriteCoordinates.giveMeThePredictionsOfMyFavoriteCoordinates();

        verify(store, times(1)).listFavoriteCoordinates();
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
