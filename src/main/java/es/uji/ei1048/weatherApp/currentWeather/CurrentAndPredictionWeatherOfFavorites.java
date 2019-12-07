package es.uji.ei1048.weatherApp.currentWeather;

import es.uji.ei1048.weatherApp.OpenWeatherMap;
import es.uji.ei1048.weatherApp.SQLiteDB;

public class CurrentAndPredictionWeatherOfFavorites {

    //TODO se podrían unificar las tres clases del controlador? lo digo porque todas usan los mismos atributos
    //o hacer algo, vamos

    private SQLiteDB sqLiteDB;
    private OpenWeatherMap openWeatherMap;

    public CurrentAndPredictionWeatherOfFavorites(){
        sqLiteDB = new SQLiteDB();
        openWeatherMap = new OpenWeatherMap();
    }

    //TODO añadir comprobación de las predicciones y borrarlas si están obsoletas
    //TODO añadir que devuelvan una lista de lugares favoritos y predicciones

}
