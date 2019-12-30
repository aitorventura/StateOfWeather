package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.OpenWeatherMap;
import es.uji.ei1048.weatherApp.model.PredictionWeather;
import es.uji.ei1048.weatherApp.model.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;

import java.util.List;

public class PredictionWeatherUsingCoordinates {
    private IStore sqLiteDB;
    private IWeatherService openWeatherMap;

    public PredictionWeatherUsingCoordinates(){
        this.sqLiteDB = new SQLiteDB();
        this.openWeatherMap = new OpenWeatherMap();

    }
    public PredictionWeatherUsingCoordinates(IStore iStore, IWeatherService iWeatherService){
        this.sqLiteDB = iStore;
        this.openWeatherMap = iWeatherService;
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
