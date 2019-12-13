package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.PredictionWeather;
import es.uji.ei1048.weatherApp.SQLiteDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredictionFavouriteCoordinates {

    private SQLiteDB sqLiteDB;
    private PredictionWeatherUsingCoordinates predictor;

    public PredictionFavouriteCoordinates() {
        this.predictor = new PredictionWeatherUsingCoordinates();
        this.sqLiteDB = new SQLiteDB();

    }


    public Map<Coordinates, List<PredictionWeather>> giveMeThePredictionsOfMyFavoriteCoordinates() {

        Map<Coordinates, List<PredictionWeather>> weatherOfFavorites = new HashMap<>();

        List<Coordinates> favoriteCities = sqLiteDB.listFavoriteCoordinates();

        for (Coordinates coordinates : favoriteCities) {
            List<PredictionWeather> listPrediction = predictor.giveMeThePredictionToTheseCoordinates(coordinates);
            weatherOfFavorites.put(coordinates, listPrediction);
        }


        return weatherOfFavorites;

    }
}