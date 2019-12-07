package es.uji.ei1048.weatherApp;

import es.uji.ei1048.weatherApp.currentWeather.CurrentWeatherUsingCity;
import es.uji.ei1048.weatherApp.currentWeather.CurrentWeatherUsingCoordinates;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;

public class WeatherAppFacade {
    CurrentWeatherUsingCity currentWeatherUsingCity;
    CurrentWeatherUsingCoordinates currentWeatherUsingCoordinates;
    Map<String, Coordinates> listOfLabels;
    WeatherOfFavoritePlaces weatherFromFavoritePlaces;
   // List<String> listOfFavouriteCities;
   // List<Double[]> listOfFavouriteCoordinates;   //No sé que estrucutra de datos es mejor para poner un vector de dos doubles


    public WeatherAppFacade(){
        this.currentWeatherUsingCity = new CurrentWeatherUsingCity();
        this.currentWeatherUsingCoordinates = new CurrentWeatherUsingCoordinates();
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


    public boolean addCityToFavorites(String city){
        /*if (!listOfFavoriteCities.contains(city)){ //Comprobar si la ciudad existe
            return listOfFavoriteCities.add(city);
        }
        return false;*/
        throw new NotImplementedException();

    }


    public boolean deleteCityFromFavorites(String city){
        //return listOfFavoriteCities.remove(city);
        throw new NotImplementedException();

    }

    public boolean addCoordinatesToFavorites(Coordinates coordinates){
        throw new NotImplementedException();
    }

    public boolean deleteCoordinatesFromFavorites(Coordinates coordinates){
        throw new NotImplementedException();
    }


    //TODO cambiar

    public List<Double[]> getListOfFavouriteCoordinates() {
        throw new NotImplementedException();
    }

    public void setListOfFavouriteCoordinates(List<Double[]> listOfFavouriteCoordinates) {
        //this.listOfFavouriteCoordinates = listOfFavouriteCoordinates;
    }

    public List<String> getListOfFavouriteCities() {
        throw new NotImplementedException();
    }

    public void setListOfFavouriteCities(List<String> listOfFavouriteCities) {
        //this.listOfFavouriteCities = listOfFavouriteCities;
    }

}
