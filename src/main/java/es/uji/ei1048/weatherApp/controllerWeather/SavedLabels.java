package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.*;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class SavedLabels {

    private IStore sqLiteDB;
    private IWeatherService weatherService;

    public SavedLabels() {
        this.sqLiteDB = new SQLiteDB();
        this.weatherService = new OpenWeatherMap();
    }

    public SavedLabels(IStore sqLiteDB, IWeatherService weatherService) {
        this.sqLiteDB = sqLiteDB;
        this.weatherService = weatherService;
    }

   /* public SavedLabels(IStore iStore, IWeatherService iWeatherService){
        this.sqLiteDB = iStore;
    }*/

    //TODO: Revisar, cambiado por @Zayda
    public Map<String, Coordinates> getAllLabels() {
        return sqLiteDB.getLabels();
        //throw new NotImplementedException();
    }

    public boolean deleteLabel(String label) {
        return sqLiteDB.removeLabel(label);
    }

    public boolean addLabel(String label, Coordinates coordinates) {

        if (coordinates.areValid()){
            return sqLiteDB.addLabel(label, coordinates);
        }
        throw new NotValidCoordinatesException();
        //return false;
    }

    public CurrentWeather getCurrentWeatherOfLabel(String label){

        Coordinates coordinates = sqLiteDB.getCoordinatesOfLabel(label);

        if (coordinates == null){
            //TODO se podría lanzar una excepción, pero por ahora se devuelve null
            //en este punto no encuenta la etiqueta que se ha pedido
            return null;
        }

        //no hace falta comprobar si las coordenadas son válidas, si están en la BBDD se han comprobado antes
        double lon = coordinates.getLon();
        double lat = coordinates.getLat();

        CurrentWeather currentWeatherOfLabel = sqLiteDB.giveMeTheCurrentWeather(lon, lat);

        if(currentWeatherOfLabel == null){ //el tiempo no está en la BBDD
            currentWeatherOfLabel = weatherService.giveMeTheCurrentWeatherUsingCoordinates(lon, lat);
           /* if(currentWeatherOfLabel == null){
                //thow NotConnectionException
            }*/
        }

        return currentWeatherOfLabel;
    }

   /* public Coordinates getCoordinatesOfLabel(String label) {
        return sqLiteDB.getCoordinatesOfLabel(label);
    }*/

}
