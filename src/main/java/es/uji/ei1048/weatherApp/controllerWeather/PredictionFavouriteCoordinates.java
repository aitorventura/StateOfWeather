package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.PredictionWeather;
import es.uji.ei1048.weatherApp.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.ThereAreNoFavouriteCities;
import es.uji.ei1048.weatherApp.exceptions.ThereAreNoFavouriteCoordinates;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredictionFavouriteCoordinates {

    private IStore sqLiteDB;
    private PredictionWeatherUsingCoordinates predictor;

    public PredictionFavouriteCoordinates() {
        this.predictor = new PredictionWeatherUsingCoordinates();
        this.sqLiteDB = new SQLiteDB();

    }

    public PredictionFavouriteCoordinates(IStore iStore){
        this.predictor = new PredictionWeatherUsingCoordinates();
        this.sqLiteDB = iStore;
    }


    public Map<Coordinates, List<PredictionWeather>> giveMeThePredictionsOfMyFavoriteCoordinates() {

        Map<Coordinates, List<PredictionWeather>> weatherOfFavorites = new HashMap<>();

        try {


            List<Coordinates> favoriteCoordinates = sqLiteDB.listFavoriteCoordinates();

            if(favoriteCoordinates == null || favoriteCoordinates.size() == 0){
                throw new ThereAreNoFavouriteCoordinates();
            }


            for (Coordinates coordinates : favoriteCoordinates) {
                List<PredictionWeather> listPrediction = predictor.giveMeThePredictionToTheseCoordinates(coordinates);
                weatherOfFavorites.put(coordinates, listPrediction);
            }
        }catch (ThereAreNoFavouriteCoordinates ex) {
            throw new ThereAreNoFavouriteCoordinates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherOfFavorites;

    }
}