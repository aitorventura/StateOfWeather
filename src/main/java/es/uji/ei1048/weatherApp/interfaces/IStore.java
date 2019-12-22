package es.uji.ei1048.weatherApp.interfaces;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.CurrentWeather;
import es.uji.ei1048.weatherApp.PredictionWeather;
import java.util.List;
import java.util.Map;

public interface IStore {

    public void open();

    public void close() throws Exception;


    //CONSULTATIONS TO ADMIN CURRENT WEATHER

    public void removeOldCurrentWeathers();

    public CurrentWeather giveMeTheCurrentWeather(String city);


    public CurrentWeather giveMeTheCurrentWeather(double lon, double lat);

    public void addCurrentWeatherToTheDataBase(CurrentWeather currentWeather);



    //CONSULTATIONS TO ADMIN PREDICTION WEATHER

    public void removeAllPredictions();

    public void removeOldPredicionWeathers();

    public List<PredictionWeather> giveMeTheListOfPredictionWeather(String city);

    public List<PredictionWeather> giveMeTheListOfPredictionWeatherUsingCoordinates(double lon, double lat);

    public void addPredictionWeatherToTheDataBase(PredictionWeather predictionWeather);


    //CONSULTATIONS TO ADMIN FAVORITECITY


    public List<String> listFavoriteCities();

    public boolean addCityToFavorite(String city);

    public boolean removeCityFromFavorite(String city);


    //CONSULTATIONS TO ADMIN FAVORITECOORDINATES



    public List<Coordinates> listFavoriteCoordinates();

    public boolean addCoordinatesToFavorite(Coordinates coordinates);

    public boolean removeCoordinatesFromFavorite(Coordinates coordinates);


    //LABELS

    public boolean addLabel(String label, Coordinates coordinates);

    public boolean removeLabel(String label);



    public Coordinates getCoordinatesOfLabel(String label);

    public Map<String, Coordinates> getLabels();

}
