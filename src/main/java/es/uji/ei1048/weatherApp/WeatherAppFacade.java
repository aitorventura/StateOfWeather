package es.uji.ei1048.weatherApp;

import es.uji.ei1048.weatherApp.controllerWeather.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherAppFacade {
    CurrentWeatherUsingCity currentWeatherUsingCity;
    CurrentWeatherUsingCoordinates currentWeatherUsingCoordinates;
    //Map<String, Coordinates> listOfLabels;
    SavedLabels savedLabels;
    FavouriteCities favouriteCities;
    FavouriteCoordinates favouriteCoordinates;
    PredictionWeatherUsingCity predictionWeatherUsingCity;
    PredictionWeatherUsingCoordinates predictionWeatherUsingCoordinates;

    public WeatherAppFacade(){
        SQLiteDB dataBase = new SQLiteDB();
        this.currentWeatherUsingCity = new CurrentWeatherUsingCity();
        this.currentWeatherUsingCoordinates = new CurrentWeatherUsingCoordinates();
        this.savedLabels = new SavedLabels();
        this.favouriteCities = new FavouriteCities();
        this.favouriteCoordinates = new FavouriteCoordinates();
        this.predictionWeatherUsingCity = new PredictionWeatherUsingCity();
        this.predictionWeatherUsingCoordinates = new PredictionWeatherUsingCoordinates();
    }


    //CURRENT WEATHER AND PREVISION

    public CurrentWeather currentWeatherCity(String city){
        return currentWeatherUsingCity.giveMeTheCurrentWeatherUsingACity(city);
    }

    public CurrentWeather currentWeatherCoordinates(Coordinates coordinates ){
        return currentWeatherUsingCoordinates.giveMeTheCurrentWeatherUsingACoordenates(coordinates.getLon(), coordinates.getLat());
    }

    public List<PredictionWeather> previsionOfWeatherCity(String city){
        return predictionWeatherUsingCity.giveMeThePredictionToThisCity(city);
    }

    public List<PredictionWeather> previsionOfWeatherCoordinates(Coordinates coordinates){
        return predictionWeatherUsingCoordinates.giveMeThePredictionToTheseCoordinates(coordinates);
    }


    //LABELS

    //añade una etiqueta
    public boolean addLabel(String label, Coordinates coordinates) {
        return savedLabels.addLabel(label, coordinates);
    }


    //borra la etiqueta elegida
    public boolean deleteLabel(String label) {
        return savedLabels.deleteLabel(label);
    }

    //lista de etiquetas (no nos hace falta en teoría
   /* public Map<String, Coordinates> getLabels(){
        return savedLabels.getLabels();
    }*/

   public CurrentWeather currentWeatherOfLabel(String label) {
       throw new NotImplementedException();
   }

    //TODO deberíamos poner un max de favoritos?

    //FAVOURITE CITIES

    public boolean addCityToFavorites(String city){
        return favouriteCities.addCityToFavourite(city);
    }


    public boolean deleteCityFromFavorites(String city){
        return favouriteCities.removeCityFromFavourite(city);
    }

    public Map<String,CurrentWeather> getListOfFavouriteCities() {

        List<String> listOfFavoriteCities = favouriteCities.getFavouriteCities();
        Map<String,CurrentWeather> weatherOfFavouriteCities = new HashMap<>();

        for(String city : listOfFavoriteCities){
            weatherOfFavouriteCities.put(city,currentWeatherUsingCity.giveMeTheCurrentWeatherUsingACity(city));
        }
        return weatherOfFavouriteCities;
    }


    //FAVOURITE COORDINATES

    public boolean addCoordinatesToFavorites(Coordinates coordinates){
        return favouriteCoordinates.addCoordinatesToFavourite(coordinates);
    }

    public boolean deleteCoordinatesFromFavorites(Coordinates coordinates){
        return favouriteCoordinates.removeCoordinatesFromFavourite(coordinates);
    }

    public Map<Coordinates, CurrentWeather> getWeatherOfFavouriteCoordinates() {

        List<Coordinates> favoriteCoordinates =favouriteCoordinates.getFavouriteCoordinates();
        Map<Coordinates,CurrentWeather> weatherOfFavouriteCoordinates = new HashMap<>();

        for(Coordinates coordinates : favoriteCoordinates){
            weatherOfFavouriteCoordinates.put(coordinates,currentWeatherUsingCoordinates.giveMeTheCurrentWeatherUsingACoordenates(coordinates.getLon(),coordinates.getLat()));
        }

        return weatherOfFavouriteCoordinates;
    }


      //PREDICTION OF FAVOURITE CITIES AND COORDENATES

    //todo implementar el controlador
    public Map<String, List<PredictionWeather>> predictionOfFavouriteCities() {
        throw new NotImplementedException();
    }

    public Map<Coordinates,List<PredictionWeather>> predictionOfFavouriteCoordenates() {
        throw new NotImplementedException();
    }
}
