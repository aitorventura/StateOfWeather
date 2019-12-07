package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.SQLiteDB;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class FavouriteCities {

    private SQLiteDB sqLiteDB;

    public FavouriteCities(){
        this.sqLiteDB = new SQLiteDB();
    }

    public List<String> getFavouriteCities(){
        return sqLiteDB.listFavoriteCities();
    }

    public boolean removeCityFromFavourite(String city){
        //todo borrarlo de la bbdd y ver si daría error al no existir la ciudad
        throw new NotImplementedException();
    }

    //todo tener en cuenta que para no repetir código, la comprobación de si existe la ciudad ya se hace en
    // CurrentWeatherUsingCity, por tanto saltará la excepción en WeatherAppFacade
    //también tener en cuenta que la ciudad podría estar repetida y también se lanzaría una excepción
    //todo tal vez habría que cambiarlo
    public boolean addCityToFavourite(String city){
        throw new NotImplementedException();
        //return sqLiteDB.addCityToFavorite(city);
    }


}
