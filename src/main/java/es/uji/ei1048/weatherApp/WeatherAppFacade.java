package es.uji.ei1048.weatherApp;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;

public class WeatherAppFacade {
    Map<String, Coordinates> listOfLabels;
    List<String> listOfFauvoriteCities;
    List<Double[]> listOfFauvoriteCoordinates;   //No sé que estrucutra de datos es mejor para poner un vector de dos doubles




    public CurrentWeather currentWeatherCity(String city){
        throw new NotImplementedException();
    }

    public CurrentWeather currentWeatherCoordinates(Coordinates coordinates ){
        throw new NotImplementedException();
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




    public List<Double[]> getListOfFauvoriteCoordinates() {
        return listOfFauvoriteCoordinates;
    }

    public void setListOfFauvoriteCoordinates(List<Double[]> listOfFauvoriteCoordinates) {
        this.listOfFauvoriteCoordinates = listOfFauvoriteCoordinates;
    }

    public List<String> getListOfFauvoriteCities() {
        return listOfFauvoriteCities;
    }

    public void setListOfFauvoriteCities(List<String> listOfFauvoriteCities) {
        this.listOfFauvoriteCities = listOfFauvoriteCities;
    }

}
