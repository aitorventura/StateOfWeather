package es.uji.ei1048.weatherApp.main;

import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import javafx.application.Platform;

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

    public void showCurrentWeather(CurrentWeather currentWeather){

        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                mainApp.initPrintCurrentWeather(currentWeather);
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

}
