package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.PredictionWeather;
import es.uji.ei1048.weatherApp.model.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;
import es.uji.ei1048.weatherApp.exceptions.ThereAreNoFavouriteCities;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class E2EPredictionListFromFavouriteWithConnection extends  E2ETestBed {
    SQLiteDB sqLiteDB;

    public E2EPredictionListFromFavouriteWithConnection(){
        super();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        sqLiteDB = new SQLiteDB();
    }


    @Test
    public void predictionOfFavouritePlacesWithConnectionAndDataLessThan1Hour() throws ThereAreNoFavouriteCities {

        //Given: hay datos de la lista de favoritos en la bbdd
        List<String> favoriteCities = sqLiteDB.listFavoriteCities();
        List<Coordinates> favoriteCoordinates = sqLiteDB.listFavoriteCoordinates();

        for(String city: favoriteCities){
            weatherAppFacade.previsionOfWeatherCity(city);
        }

        for (Coordinates coordinates: favoriteCoordinates){
            weatherAppFacade.previsionOfWeatherCoordinates(coordinates);
        }

        //When: entro en la aplicación, hay conexión y datos de menos de XXX
        Map<String, List<PredictionWeather>> predictionOfFavouriteCities = weatherAppFacade.predictionOfFavouriteCities();
        Map<Coordinates, List<PredictionWeather>> predictionOfFavouroteCoordinates = weatherAppFacade.predictionOfFavouriteCoordenates();

        //Then: se muestran los datos
        int numberOfFavorites = sqLiteDB.listFavoriteCities().size();
        int numberOfFavoritesCoordinates = sqLiteDB.listFavoriteCoordinates().size();

        assertTrue(numberOfFavorites == predictionOfFavouriteCities.size());

        for (String s: predictionOfFavouriteCities.keySet()){
            assertTrue(3 == predictionOfFavouriteCities.get(s).size());
        }

        assertTrue(numberOfFavoritesCoordinates == predictionOfFavouroteCoordinates.size());

        for (Coordinates coordinates: predictionOfFavouroteCoordinates.keySet()){
            assertTrue(3 == predictionOfFavouroteCoordinates.get(coordinates).size());
        }

    }

    @Test
    public void predictionOfFavouritePlacesWithConnectionAndWithoutDataLessThan1Hour() throws NotValidCityException{

        //Given: no hay datos
        sqLiteDB.removeAllPredictions();

        //When: entro en la aplicación, hay conexión, pero no datos de menos de XXX en la BBDD
        Map<String, List<PredictionWeather>> predictionOfFavouriteCities = weatherAppFacade.predictionOfFavouriteCities();
        Map<Coordinates, List<PredictionWeather>> predictionOfFavouriteCoordinates = weatherAppFacade.predictionOfFavouriteCoordenates();

        //Then: se muestran los datos
        int numberOfFavorites = sqLiteDB.listFavoriteCities().size();
        int numberOfFavoritesCoordinates = sqLiteDB.listFavoriteCoordinates().size();

        assertTrue(numberOfFavorites == predictionOfFavouriteCities.size());

        for (String s: predictionOfFavouriteCities.keySet()){
            assertTrue(3 == predictionOfFavouriteCities.get(s).size());
        }

        assertTrue(numberOfFavoritesCoordinates == predictionOfFavouriteCoordinates.size());

        for (Coordinates coordinates: predictionOfFavouriteCoordinates.keySet()){
            assertTrue(3 == predictionOfFavouriteCoordinates.get(coordinates).size());
        }
    }
}
