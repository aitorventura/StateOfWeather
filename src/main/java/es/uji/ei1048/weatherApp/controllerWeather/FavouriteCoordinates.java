package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.SQLiteDB;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;

import java.util.List;

public class FavouriteCoordinates {

    private IStore sqLiteDB;

    public FavouriteCoordinates(IStore iStore){
        this.sqLiteDB = iStore;
    }

    public FavouriteCoordinates(){
        this.sqLiteDB = new SQLiteDB();
    }

    //getFavoriteCoordinates, addCoordinatesToFavorite, removeCoordinates - ver si las coordenadas son válidas
    //todo valdria la pena guardarse una lista con las coordenadas? lo mismo con las ciudades

    public List<Coordinates> getFavouriteCoordinates(){
        return sqLiteDB.listFavoriteCoordinates();
    }

    public boolean addCoordinatesToFavourite(Coordinates coordinates){

        if(coordinates.areValid()){
            return sqLiteDB.addCoordinatesToFavorite(coordinates);
        }

        return false;
    }

    public boolean removeCoordinatesFromFavourite(Coordinates coordinates){
       // throw new NotImplementedException();

        if(coordinates.areValid()){
            List<Coordinates> coodinatesFav = sqLiteDB.listFavoriteCoordinates();

            //TODO se dejará así si también tenemos que devolver false cuando la coordenada no existe
            //yo prefiero quitarlo, se puede hacer en la vista una lista un botón para borrar un sitio
            //por tanto, siempre existirá en la bbdd y sino existe nos da igual también
            if(coodinatesFav.contains(coordinates)){
                return sqLiteDB.removeCoordinatesFromFavorite(coordinates);
            }
        }

        return false;
    }
}
