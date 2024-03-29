package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.model.OpenWeatherMap;
import es.uji.ei1048.weatherApp.model.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;

public class CurrentWeatherUsingCity {
    private IStore sqLiteDB;
    private IWeatherService openWeatherMap;

    public CurrentWeatherUsingCity(){
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();

    }

    public CurrentWeatherUsingCity(IStore iStore, IWeatherService iWeatherService){
        this.sqLiteDB = iStore;
        this.openWeatherMap = iWeatherService;
    }

    public CurrentWeather giveMeTheCurrentWeatherUsingACity(String city){
        sqLiteDB.removeOldCurrentWeathers(); //se borran todos los datos meteorológicos obsoletos

        CurrentWeather currentWeather = sqLiteDB.giveMeTheCurrentWeather(city); //se extrae el dato de la BBDD

        if(currentWeather == null){ //si no existe, se consulta al servidor
            currentWeather = this.openWeatherMap.giveMeTheCurrentWeatherUsingACity(city);
        } else {
            return currentWeather; //si existe en la BBDD, se devuelve el dato
        }
        //TODO; Cambiar el nombre de la excepción para hacerla global
        if(currentWeather == null){ //si sigue sin existir, la ciudad no es válida o no hay conexión
            throw new NotValidCityException();
        } else {
            sqLiteDB.addCurrentWeatherToTheDataBase(currentWeather); //si existe, se añade a la BBDD
            return  currentWeather;
        }

    }





}
