package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.CurrentWeather;
import es.uji.ei1048.weatherApp.OpenWeatherMap;
import es.uji.ei1048.weatherApp.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;

public class CurrentWeatherUsingCoordinates {
    private IStore sqLiteDB;
    private IWeatherService openWeatherMap;

    public CurrentWeatherUsingCoordinates() {
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();
    }

    public CurrentWeatherUsingCoordinates(IStore iStore, IWeatherService iWeatherService){
        this.sqLiteDB = iStore;
        this.openWeatherMap = iWeatherService;
    }


    public CurrentWeather giveMeTheCurrentWeatherUsingACoordenates(double lon, double lat) {

        sqLiteDB.removeOldCurrentWeathers(); //borra todos los datos obsoletos

        CurrentWeather currentWeather = sqLiteDB.giveMeTheCurrentWeather(lon, lat);

        if (currentWeather == null) { //si las coordenadas NO están en la BBDD
            currentWeather = this.openWeatherMap.giveMeTheCurrentWeatherUsingCoordinates(lon, lat);
        } else {
            return currentWeather; //si las coordenadas están en la BBDD
        }

        //TODO nos podríamos ahorrar esta comprobación si al principio creamos la coordenada y ejectumanos su método areValid()
        if (currentWeather == null) { //si no existe la coordenada
            throw new NotValidCoordinatesException();
        } else { //si existe, se añade
            sqLiteDB.addCurrentWeatherToTheDataBase(currentWeather);
            return currentWeather;
        }

    }
}
