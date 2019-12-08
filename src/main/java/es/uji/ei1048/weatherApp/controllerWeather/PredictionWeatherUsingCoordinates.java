package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.*;
import es.uji.ei1048.weatherApp.exceptions.NotValidCityException;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;

import java.util.List;

public class PredictionWeatherUsingCoordinates {
    private SQLiteDB sqLiteDB;
    private OpenWeatherMap openWeatherMap;


    public PredictionWeatherUsingCoordinates(){
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();

    }



    public List<PredictionWeather> giveMeThePredictionToTheseCoordinates(Coordinates coordinates){

        sqLiteDB.removeOldPredicionWeathers(); //se borran aquellos datos que no sean de prodicciones futuras

        List<PredictionWeather> listPredictions = sqLiteDB.giveMeTheListOfPredictionWeatherUsingCoordinates(coordinates.getLon(), coordinates.getLat()); //devuelve lista si la hay

        if(listPredictions == null || listPredictions.size() < 3) { //si no existe, se consulta al servidor
            listPredictions = this.openWeatherMap.giveMeTheListOfPredictionsUsingCoordinates(coordinates.getLon(), coordinates.getLat());
        } else {
            return listPredictions; //si existe en la BBDD, se devuelve el dato
        }

        if(listPredictions == null || listPredictions.size() == 0){ //si sigue sin existir, la ciudad no es válida
            throw new NotValidCoordinatesException();
        } else {
            for(PredictionWeather p: listPredictions){
                sqLiteDB.addPredictionWeatherToTheDataBase(p);
            }
            return  listPredictions;

        }
    }
}
