package es.uji.ei1048.weatherApp;

import es.uji.ei1048.weatherApp.controllerWeather.CurrentWeatherUsingCity;
import es.uji.ei1048.weatherApp.controllerWeather.CurrentWeatherUsingCoordinates;
import es.uji.ei1048.weatherApp.controllerWeather.FavouriteCities;
import es.uji.ei1048.weatherApp.controllerWeather.FavouriteCoordinates;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherAppFacade {
    CurrentWeatherUsingCity currentWeatherUsingCity;
    CurrentWeatherUsingCoordinates currentWeatherUsingCoordinates;
    Map<String, Coordinates> listOfLabels;
    FavouriteCities favouriteCities;
    FavouriteCoordinates favouriteCoordinates;
    //si acaso añadir un Map de coordenadas y ciudades favoritas

    public WeatherAppFacade(){
        this.currentWeatherUsingCity = new CurrentWeatherUsingCity();
        this.currentWeatherUsingCoordinates = new CurrentWeatherUsingCoordinates();
        this.favouriteCities = new FavouriteCities();
        this.favouriteCoordinates = new FavouriteCoordinates();
    }

    public CurrentWeather currentWeatherCity(String city){
        return currentWeatherUsingCity.giveMeTheCurrentWeatherUsingACity(city);
    }

    public CurrentWeather currentWeatherCoordinates(Coordinates coordinates ){
        return currentWeatherUsingCoordinates.giveMeTheCurrentWeatherUsingACoordenates(coordinates.getLon(), coordinates.getLat());
    }

    public List<PredictionWeather> previsionOfWeatherCity(String city){
        throw new NotImplementedException();
    }

    public List<PredictionWeather> previsionOfWeatherCoordinates(Coordinates coordinates){
        throw new NotImplementedException();
    }

    public boolean addLabel(String label, Coordinates coordinates) {
        throw new NotImplementedException();
    }



    //Devuelve el elemento que ha borrado o null si no existía
    public boolean deleteLabel(String nameOfLabel) {
        //Se debe controlar que se borre el elemento pedido
        throw new NotImplementedException();

        //return listOfLabels.remove(nameOfLabel);
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

   /* public void setListOfFavouriteCities(List<String> listOfFavouriteCities) {
        this.listOfFavouriteCities = listOfFavouriteCities;
    }*/

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

    /*public void setListOfFavouriteCoordinates(List<Double[]> listOfFavouriteCoordinates) {
        this.listOfFavouriteCoordinates = listOfFavouriteCoordinates;
    }*/

    //PREDICTION OF FAVOURITE CITIES AND COORDENATES

    //todo implementar el controlador
    public Map<String, List<PredictionWeather>> predictionOfFavouriteCities() {
        throw new NotImplementedException();
    }

    public Map<Coordinates,List<PredictionWeather>> predictionOfFavouriteCoordenates() {
        throw new NotImplementedException();
    }
}
