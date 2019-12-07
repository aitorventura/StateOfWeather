package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.CurrentWeather;
import es.uji.ei1048.weatherApp.OpenWeatherMap;
import es.uji.ei1048.weatherApp.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;

public class CurrentWeatherUsingCoordinates {
    private SQLiteDB sqLiteDB;
    private OpenWeatherMap openWeatherMap;

    public CurrentWeatherUsingCoordinates() {
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();


    }

    public CurrentWeather giveMeTheCurrentWeatherUsingACoordenates(double lon, double lat) {
        sqLiteDB.removeOldCurrentWeathers();

        CurrentWeather currentWeather = sqLiteDB.giveMeTheCurrentWeather(lon, lat);

        if (currentWeather == null) {
            currentWeather = this.openWeatherMap.giveMeTheCurrentWeatherUsingCoordinates(lon, lat);
        } else {
            return currentWeather;
        }

        if (currentWeather == null) {
            throw new NotValidCoordinatesException();
        } else {
            sqLiteDB.addCurrentWeatherToTheDataBase(currentWeather);
            return currentWeather;


        }

    }
}
