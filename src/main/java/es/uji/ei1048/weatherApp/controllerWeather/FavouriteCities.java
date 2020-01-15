package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.model.OpenWeatherMap;
import es.uji.ei1048.weatherApp.model.SQLiteDB;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;


import java.util.List;

public class FavouriteCities {

    private IStore sqLiteDB;
    private IWeatherService openWeatherMap;

    public FavouriteCities(){
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();
    }

    public FavouriteCities(IStore iStore, IWeatherService iWeatherService){
        this.sqLiteDB = iStore;
        this.openWeatherMap = iWeatherService;
    }


    public List<String> getFavouriteCities(){
        return sqLiteDB.listFavoriteCities();
    }

    public boolean removeCityFromFavourite(String city){
        return sqLiteDB.removeCityFromFavorite(city);
    }


    public boolean addCityToFavourite(String city){

        CurrentWeather currentWeather = openWeatherMap.giveMeTheCurrentWeatherUsingACity(city);

        if(currentWeather == null){
            return false;
        }

        return sqLiteDB.addCityToFavorite(currentWeather.getCity());
    }


}
