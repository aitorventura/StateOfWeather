package es.uji.ei1048.weatherApp.main;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.model.PredictionWeather;
import javafx.application.Platform;

import java.util.*;

public class HelperControllerMain {

    private MainApp mainApp;
    private WeatherAppFacade weatherAppFacade;

    public HelperControllerMain(MainApp mainApp){
        this.mainApp = mainApp;
        this.weatherAppFacade = new WeatherAppFacade();
    }



    public Set<String> getLabels(){
        Map<String, Coordinates> mapLabels = weatherAppFacade.getAllLabels();

        Set<String> listLabels = mapLabels.keySet();


        return listLabels;


    }

    public List<String> getListOfFavourites(){
        List<Coordinates> listCoordinates = weatherAppFacade.getListOfFavouriteCoordinates();
        List<String> listCities = weatherAppFacade.getListOfFavouriteCities();

        List<String> listFavourites = new ArrayList<>();
        listFavourites.addAll(listCities);

        for(Coordinates c: listCoordinates){
            listFavourites.add(c.toString());
        }

        return listFavourites;
    }
    public void removeLabel(String label){
        weatherAppFacade.deleteLabel(label);
    }

    public boolean addNewLabel(String label, Coordinates coordinates){
        return weatherAppFacade.addLabel(label, coordinates);
    }


    public CurrentWeather getCurrentWeatherUsingLabel(String label){
        return weatherAppFacade.currentWeatherOfLabel(label);
    }

    public void openCurrentPredictionWeatherView() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                mainApp.initCurrentPredictionWeather();
            }

        });


    }

    public CurrentWeather getCurrentWeatherOfCity(String city){
        return weatherAppFacade.currentWeatherCity(city);
    }

    public CurrentWeather getCurrentWeatherOfCoordinates(Coordinates coordinates){
        return weatherAppFacade.currentWeatherCoordinates(coordinates);
    }

    public List<PredictionWeather> getPredictionWeatherOfCoordinates(Coordinates coordinates){
        return weatherAppFacade.previsionOfWeatherCoordinates(coordinates);
    }

    public List<PredictionWeather> getPredictionWeatherOfCity(String city){
        return weatherAppFacade.previsionOfWeatherCity(city);
    }

    public void showCurrentWeather(CurrentWeather currentWeather){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println(currentWeather.toString());
                mainApp.initPrintCurrentWeather(currentWeather);

            }
        });




    }

    public void showPredictionWeather(List<PredictionWeather> list){

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                mainApp.initPrintPredictionWeather(list);
            }

        });

    }

    public void showSaveLabels() {

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                mainApp.initSavedLabelsView();
            }

        });
    }

    public void showFavourites(){  Platform.runLater(new Runnable() {

        @Override
        public void run() {
            mainApp.initFavouritesView();
        }

    });

    }


    public void showErrorCityOrCoordinates(){
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                mainApp.initErrorCityOrCoordinates();
            }

        });


    }

    public boolean addNewFavouriteCoordinate(Coordinates coordinates) {
        return weatherAppFacade.addCoordinatesToFavorites(coordinates);
    }

    public boolean addNewFavouriteCity(String city) {
        return weatherAppFacade.addCityToFavorites(city);
    }

    public boolean removeFavouriteCoordinates(Coordinates coordinates) {
        return weatherAppFacade.deleteCoordinatesFromFavorites(coordinates);
    }

    public boolean removeFavouriteCity(String city) {
        return weatherAppFacade.deleteCityFromFavorites(city);
    }

    public void showTheMap(String option) {
        weatherAppFacade.openTheMap(option);
    }

    public String[] getListOfOptions() {
        return weatherAppFacade.getListOfMeterologicalPhenomenon();
    }

    public void showOpenTheMap() { Platform.runLater(new Runnable() {

        @Override
        public void run() {
            mainApp.initOpenAMap();
        }

    });
    }
}
