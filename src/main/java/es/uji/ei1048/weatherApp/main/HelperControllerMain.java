package es.uji.ei1048.weatherApp.main;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.model.PredictionWeather;
import javafx.application.Platform;

import java.util.List;

public class HelperControllerMain {

    private MainApp mainApp;
    private WeatherAppFacade weatherAppFacade;

    public HelperControllerMain(MainApp mainApp){
        this.mainApp = mainApp;
        this.weatherAppFacade = new WeatherAppFacade();
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

    public void showErrorCityOrCoordinates(){
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                mainApp.initErrorCityOrCoordinates();
            }

        });


    }
}
