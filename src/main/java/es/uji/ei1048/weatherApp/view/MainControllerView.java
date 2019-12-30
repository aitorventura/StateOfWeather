package es.uji.ei1048.weatherApp.view;

import es.uji.ei1048.weatherApp.main.HelperControllerMain;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainControllerView {

    private HelperControllerMain helperControllerMain;

    @FXML
    Button buttonCurrentPredictionWeather;

    @FXML
    Button buttonFavourites;

    @FXML
    Button buttonSavedLabels;

    @FXML
    Button buttonOpenMap;

    public void setHelperControllerMain(HelperControllerMain helperControllerMain){
        this.helperControllerMain = helperControllerMain;
    }

    @FXML
    private void handleButtonCurrentPredictionWeather(){
        this.helperControllerMain.openCurrentPredictionWeatherView();
    }

    @FXML
    private void handleButtonFavourites(){

    }

    @FXML
    private void handleButtonSavedLabels(){
        this.helperControllerMain.showSaveLabels();
    }

    @FXML
    private void handleButtonOpenMap(){

    }


}
