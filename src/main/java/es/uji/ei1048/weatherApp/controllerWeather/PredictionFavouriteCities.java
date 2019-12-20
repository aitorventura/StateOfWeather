package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.PredictionWeather;
import es.uji.ei1048.weatherApp.SQLiteDB;
import es.uji.ei1048.weatherApp.WeatherAppFacade;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredictionFavouriteCities {

    private IStore sqLiteDB;
    private PredictionWeatherUsingCity predictor;
    //private List<Coordinates> listOfFavouriteCoordinates;

    public PredictionFavouriteCities(){
        this.predictor = new PredictionWeatherUsingCity();
        this.sqLiteDB = new SQLiteDB();
    }
    public PredictionFavouriteCities(IStore iStore){
        this.predictor = new PredictionWeatherUsingCity();
        this.sqLiteDB = iStore;
    }



    public Map<String, List<PredictionWeather>> giveMeThePredictionsOfMyFavoriteCities(){

        Map<String, List<PredictionWeather>> weatherOfFavorites = new HashMap<>();

        List<String> favoriteCities = sqLiteDB.listFavoriteCities();

        for(String city: favoriteCities){
            List<PredictionWeather> listPrediction = predictor.giveMeThePredictionToThisCity(city);
            weatherOfFavorites.put(city, listPrediction);
        }


        return weatherOfFavorites;

    }















}