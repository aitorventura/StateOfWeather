package es.uji.ei1048.weatherApp.controllerWeather;

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
        sqLiteDB.removeOldCurrentWeathers(); //se borran todos los datos meteorológicos obsoletos

        CurrentWeather currentWeather = sqLiteDB.giveMeTheCurrentWeather(city); //se extrae el dato de la BBDD

        if(currentWeather == null){ //si no existe, se consulta al servidor
            currentWeather = this.openWeatherMap.giveMeTheCurrentWeatherUsingACity(city);
        } else {
            return currentWeather; //si existe en la BBDD, se devuelve el dato
        }

        if(currentWeather == null){ //si sigue sin existir, la ciudad no es válida
            throw new NotValidCityException();
        } else {
            sqLiteDB.addCurrentWeatherToTheDataBase(currentWeather); //si existe, se añade a la BBDD
            return  currentWeather;


        }

    }





}
