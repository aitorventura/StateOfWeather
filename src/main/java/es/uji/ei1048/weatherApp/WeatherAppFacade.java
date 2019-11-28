package es.uji.ei1048.weatherApp;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;
import java.util.Vector;

public class WeatherAppFacade {
    Map<String, Label> listOfLabels;
    List<String> listOfFavoriteCities;
    List<Double[]> listOfFavoriteCoordinates;   //No sé que estrucutra de datos es mejor para poner un vector de dos doubles


    public CurrentWeather currentWeatherCity(String city){
        throw new NotImplementedException();
    }

    public CurrentWeather currentWeatherCoordinates(double longitude, double latitude ){
        throw new NotImplementedException();
    }

    public List<PredictionWeather> previsionOfWeatherCity(String city){
        throw new NotImplementedException();
    }

    public List<PredictionWeather> previsionOfWeatherCoordinates(double longitude, double latitude ){
        throw new NotImplementedException();
    }

    public void addLabel(Label label) {
        listOfLabels.put(label.getLabel(), label);
    }

    public boolean containsLabel(String labelName) {
        if (listOfLabels.containsKey(labelName)){
            //return listOfLabels.get(labelName);
            return true;
        }
        return false;
    }

    //Devuelve el elemento que ha borrado o null si no existía
    public Label deleteLabel(String nameOfLabel) {
        return listOfLabels.remove(nameOfLabel);

    }


    public boolean addCityToFavorites(String city){
        if (!listOfFavoriteCities.contains(city)){ //Comprobar si la ciudad existe
            return listOfFavoriteCities.add(city);
        }
        return false;
    }


    public boolean deleteCityToFavorites(String city){
        return listOfFavoriteCities.remove(city);
    }


    //Falta el addCoordinatesToFavorites y el deleteCityToFavorites
}
