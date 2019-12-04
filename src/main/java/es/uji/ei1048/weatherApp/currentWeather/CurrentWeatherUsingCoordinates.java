package es.uji.ei1048.weatherApp.currentWeather;

import es.uji.ei1048.weatherApp.CurrentWeather;
import es.uji.ei1048.weatherApp.OpenWeatherMap;
import es.uji.ei1048.weatherApp.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;

public class CurrentWeatherUsingCoordinates {
    private SQLiteDB sqLiteDB;
    private OpenWeatherMap openWeatherMap;

    public CurrentWeatherUsingCoordinates() {
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();


    }

    public CurrentWeather giveMeTheCurrentWeatherUsingACoordenates(double lon, double lat) {
        sqLiteDB.removeOldCurrentTimes();

        CurrentWeather currentWeather = sqLiteDB.giveMeTheCurrentWeather(lon, lat);

        if (currentWeather == null) {
            currentWeather = this.openWeatherMap.giveMeTheCurrentTimeUsingCoordinates(lon, lat);
        } else {
            return currentWeather;
        }

        if (currentWeather == null) {
            throw new NotValidCoordinatesException();
        } else {
            sqLiteDB.addCurrentWatherToTheDataBase(currentWeather);
            return currentWeather;


        }

    }
}
