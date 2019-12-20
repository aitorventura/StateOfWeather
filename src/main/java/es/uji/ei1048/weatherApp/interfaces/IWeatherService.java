package es.uji.ei1048.weatherApp.interfaces;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.CurrentWeather;
import es.uji.ei1048.weatherApp.PredictionWeather;
import java.util.List;


public interface IWeatherService {


    public CurrentWeather giveMeTheCurrentWeatherUsingACity(String city);

    public CurrentWeather giveMeTheCurrentWeatherUsingCoordinates(double lon, double lat);



    public List<PredictionWeather> giveMeTheListOfPredictionsUsingACity(String city);



    public List<PredictionWeather> giveMeTheListOfPredictionsUsingCoordinates(double lon, double lat);

    public void openWeatherMap(Coordinates coordinates, String typeOfMap); //if coordenadas == null -> mapa españa double lon   = -4.1968; double lat =  40.220478;

}
