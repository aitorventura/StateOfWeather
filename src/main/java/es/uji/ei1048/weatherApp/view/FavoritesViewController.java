package es.uji.ei1048.weatherApp.view;

import es.uji.ei1048.weatherApp.main.HelperControllerMain;
import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoritesViewController {

    HelperControllerMain helperControllerMain;

    @FXML
    TextField nameOrCorrdinates;

    @FXML
    ChoiceBox<String> favourites;

    @FXML
    Text message;

    @FXML
    Text errorMessage;

    public void setHelperControllerMain(HelperControllerMain helperControllerMain){
        this.helperControllerMain = helperControllerMain;

        List<String> listFavourites = helperControllerMain.getListOfFavourites();

        for(String favourite: listFavourites){
            favourites.getItems().add(favourite);
        }

    }

    @FXML
    private void handleButtonGetCurrentWeather() throws IOException {



    }


    @FXML
    private void handleButtonGetForecastWeather() throws IOException {



    }


    @FXML
    private void handleAddNewLabel() throws IOException {


    }



    @FXML
    private void handleDeleteLabel() throws IOException {


    }













}
