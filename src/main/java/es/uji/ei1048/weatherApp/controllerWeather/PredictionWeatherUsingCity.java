package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.CurrentWeather;
import es.uji.ei1048.weatherApp.OpenWeatherMap;
import es.uji.ei1048.weatherApp.PredictionWeather;
import es.uji.ei1048.weatherApp.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;

import java.util.List;

public class PredictionWeatherUsingCity {
    private IStore sqLiteDB;
    private IWeatherService openWeatherMap;


    public PredictionWeatherUsingCity(){
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();

    }

    public PredictionWeatherUsingCity(IStore sqLiteDB, IWeatherService openWeatherMap) {
        this.sqLiteDB = sqLiteDB;
        this.openWeatherMap = openWeatherMap;
    }

    public List<PredictionWeather> giveMeThePredictionToThisCity(String city){

        sqLiteDB.removeOldPredicionWeathers(); //se borran aquellos datos que no sean de prodicciones futuras

        List<PredictionWeather> listPredictions = sqLiteDB.giveMeTheListOfPredictionWeather(city); //devuelve lista si la hay

        if(listPredictions == null || listPredictions.size() < 3) { //si no existe, se consulta al servidor
            listPredictions = this.openWeatherMap.giveMeTheListOfPredictionsUsingACity(city);
        } else {
            return listPredictions; //si existe en la BBDD, se devuelve el dato
        }

        if(listPredictions == null || listPredictions.size() == 0){ //si sigue sin existir, la ciudad no es válida
            throw new NotValidCityException();
        } else {
            for(PredictionWeather p: listPredictions){
                sqLiteDB.addPredictionWeatherToTheDataBase(p);
            }
            return  listPredictions;

        }
    }
}
