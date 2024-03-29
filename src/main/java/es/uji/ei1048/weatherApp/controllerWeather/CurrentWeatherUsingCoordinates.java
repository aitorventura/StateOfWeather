package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.model.OpenWeatherMap;
import es.uji.ei1048.weatherApp.model.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.NoConnectionException;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrentWeatherUsingCoordinates {
    private IStore sqLiteDB;
    private IWeatherService openWeatherMap;

    public CurrentWeatherUsingCoordinates() {
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();
    }

    public CurrentWeatherUsingCoordinates(IStore iStore, IWeatherService iWeatherService){
        this.sqLiteDB = iStore;
        this.openWeatherMap = iWeatherService;

    }


    //TODO tal vez sería mejor cambiar el método para que se le pasara directamente un objeto Coordinate
    //es como está en el resto de controladores
    public CurrentWeather giveMeTheCurrentWeatherUsingACoordinates(double lon, double lat) {


        //comprobación para saber si son válidas las coordenadas
        Coordinates coordinates = new Coordinates(lon, lat);
        if(!coordinates.areValid()){
            throw new NotValidCoordinatesException();
        }

        sqLiteDB.removeOldCurrentWeathers(); //borra todos los datos obsoletos

        CurrentWeather currentWeather = sqLiteDB.giveMeTheCurrentWeather(coordinates.getLon(), coordinates.getLat());

        if (currentWeather == null) { //si las coordenadas NO están en la BBDD
            currentWeather = this.openWeatherMap.giveMeTheCurrentWeatherUsingCoordinates(coordinates.getLon(), coordinates.getLat());
            if (currentWeather == null){ //no hay conexión
                throw  new NoConnectionException();
            }
        } else {
            return currentWeather; //si las coordenadas están en la BBDD
        }

        sqLiteDB.addCurrentWeatherToTheDataBase(currentWeather);
        return currentWeather;

    }
}
