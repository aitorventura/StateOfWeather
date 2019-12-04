package es.uji.ei1048.weatherApp.currentWeather;

import es.uji.ei1048.weatherApp.CurrentWeather;
import es.uji.ei1048.weatherApp.OpenWeatherMap;
import es.uji.ei1048.weatherApp.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;

public class CurrentWeatherUsingCity {
    private SQLiteDB sqLiteDB;
    private OpenWeatherMap openWeatherMap;

    public CurrentWeatherUsingCity(){
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();



    }

    public CurrentWeather giveMeTheCurrentWeatherUsingACity(String city){
        sqLiteDB.removeOldCurrentTimes();

        CurrentWeather currentWeather = sqLiteDB.giveMeTheCurrentWeather(city);

        if(currentWeather == null){
            currentWeather = this.openWeatherMap.giveMeTheCurrentTimeUsingACity(city);
        } else {
            return currentWeather;
        }

        if(currentWeather == null){
            throw new NotValidCityException();
        } else {
            sqLiteDB.addCurrentWatherToTheDataBase(currentWeather);
            return  currentWeather;


        }

    }





}
