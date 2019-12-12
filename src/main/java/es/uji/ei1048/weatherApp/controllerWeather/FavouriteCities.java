package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.CurrentWeather;
import es.uji.ei1048.weatherApp.OpenWeatherMap;
import es.uji.ei1048.weatherApp.SQLiteDB;
import es.uji.ei1048.weatherApp.Weather;
import org.omg.CORBA.Current;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class FavouriteCities {

    private SQLiteDB sqLiteDB;
    private OpenWeatherMap openWeatherMap;

    public FavouriteCities(){

        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();
    }

    public List<String> getFavouriteCities(){
        return sqLiteDB.listFavoriteCities();
    }

    public boolean removeCityFromFavourite(String city){
        //todo borrarlo de la bbdd y ver si daría error al no existir la ciudad
        return sqLiteDB.removeCityFromFavorite(city);
    }

    //todo tener en cuenta que para no repetir código, la comprobación de si existe la ciudad ya se hace en
    // CurrentWeatherUsingCity, por tanto saltará la excepción en WeatherAppFacade
    //también tener en cuenta que la ciudad podría estar repetida y también se lanzaría una excepción
    //todo tal vez habría que cambiarlo
    public boolean addCityToFavourite(String city){

        CurrentWeather currentWeather = openWeatherMap.giveMeTheCurrentWeatherUsingACity(city);

        if(currentWeather == null){
            return false;
        }

        return sqLiteDB.addCityToFavorite(city);
    }


}
