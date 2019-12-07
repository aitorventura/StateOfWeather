package es.uji.ei1048.weatherApp;

import java.util.Map;

public class WeatherOfFavoritePlaces {

    //List<String> listOfFavouriteCities;
    //List<Double[]> listOfFavouriteCoordinates;   //No sé que estrucutra de datos es mejor para poner un vector de dos doubles

    Map<String,CurrentWeather> listOfFavouriteCities;
    Map<Coordinates,CurrentWeather> listOfFavouriteCoordinates;

    Map<String,PredictionWeather []> predictionWeatherListOfFavouriteCities;
    Map<Coordinates,PredictionWeather []> predictionWeatherListOfFavouriteCoordinates;


    public Map<String, CurrentWeather> getListOfFavouriteCities() {
        return listOfFavouriteCities;
    }

    public void setListOfFavouriteCities(Map<String, CurrentWeather> listOfFavouriteCities) {
        this.listOfFavouriteCities = listOfFavouriteCities;
    }

    public Map<Coordinates, CurrentWeather> getListOfFavouriteCoordinates() {
        return listOfFavouriteCoordinates;
    }

    public void setListOfFavouriteCoordinates(Map<Coordinates, CurrentWeather> listOfFavouriteCoordinates) {
        this.listOfFavouriteCoordinates = listOfFavouriteCoordinates;
    }

    public Map<String, PredictionWeather[]> getPredictionWeatherListOfFavouriteCities() {
        return predictionWeatherListOfFavouriteCities;
    }

    public void setPredictionWeatherListOfFavouriteCities(Map<String, PredictionWeather[]> predictionWeatherListOfFavouriteCities) {
        this.predictionWeatherListOfFavouriteCities = predictionWeatherListOfFavouriteCities;
    }

    public Map<Coordinates, PredictionWeather[]> getPredictionWeatherListOfFavouriteCoordinates() {
        return predictionWeatherListOfFavouriteCoordinates;
    }

    public void setPredictionWeatherListOfFavouriteCoordinates(Map<Coordinates, PredictionWeather[]> predictionWeatherListOfFavouriteCoordinates) {
        this.predictionWeatherListOfFavouriteCoordinates = predictionWeatherListOfFavouriteCoordinates;
    }
}
