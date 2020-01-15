package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.interfaces.IWeatherService;
import es.uji.ei1048.weatherApp.model.*;
import es.uji.ei1048.weatherApp.interfaces.IStore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class FavouriteCoordinates {

    private IStore sqLiteDB;
    private IWeatherService openWeatherMap;

    public FavouriteCoordinates(IStore iStore){
        this.sqLiteDB = iStore;
    }

    public FavouriteCoordinates(){
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();
    }

    public FavouriteCoordinates(IStore iStore, IWeatherService iWeatherService){
        this.sqLiteDB = iStore;
        this.openWeatherMap = iWeatherService;
    }


    //getFavoriteCoordinates, addCoordinatesToFavorite, removeCoordinates - ver si las coordenadas son válidas
    //todo valdria la pena guardarse una lista con las coordenadas? lo mismo con las ciudades

    public List<Coordinates> getFavouriteCoordinates(){
        return sqLiteDB.listFavoriteCoordinates();
    }

    public boolean addCoordinatesToFavourite(Coordinates coordinates){

        if(coordinates.areValid()){
            CurrentWeather currentWeather = openWeatherMap.giveMeTheCurrentWeatherUsingCoordinates(coordinates.getLon(), coordinates.getLat());

            if(currentWeather!= null){
                 return sqLiteDB.addCoordinatesToFavorite(currentWeather.getCoordinates());
            }
        }

        return false;
    }

    public boolean removeCoordinatesFromFavourite(Coordinates coordinates){
       // throw new NotImplementedException();

        if(coordinates.areValid()){
            List<Coordinates> coodinatesFav = sqLiteDB.listFavoriteCoordinates();
            //yo prefiero quitarlo, se puede hacer en la vista una lista un botón para borrar un sitio
            //por tanto, siempre existirá en la bbdd y sino existe nos da igual también
            if(coodinatesFav.contains(coordinates)){
                return sqLiteDB.removeCoordinatesFromFavorite(coordinates);
            }
        }

        return false;
    }
}
