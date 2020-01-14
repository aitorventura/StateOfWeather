package es.uji.ei1048.weatherApp.view;

import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import es.uji.ei1048.weatherApp.main.HelperControllerMain;
import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.model.PredictionWeather;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class FavoritesViewController {

    HelperControllerMain helperControllerMain;

    @FXML
    Button deleteFavourite;

    @FXML
    Button addFavourite;

    @FXML
    Button currentWeather;

    @FXML
    Button forecastWeather;

    @FXML
    TextField coordinatesCity;

    @FXML
    ChoiceBox<String> type;

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

        type.getItems().add("Coordinates");
        type.getItems().add("City");

    }

    @FXML
    private void handleButtonGetCurrentWeather() throws IOException {

        CurrentWeather currentWeather;
        message.setText("");
        errorMessage.setText("");
        String option = favourites.getSelectionModel().getSelectedItem();

        try{

            Coordinates coordinates = getCoordinatesFromOption(option);
            currentWeather = helperControllerMain.getCurrentWeatherOfCoordinates(coordinates);
            helperControllerMain.showCurrentWeather(currentWeather);


        }catch (Exception e){
            try {
                currentWeather = helperControllerMain.getCurrentWeatherOfCity(option);
                helperControllerMain.showCurrentWeather(currentWeather);

            }catch (Exception ex){
                helperControllerMain.showErrorCityOrCoordinates();
            }
        }


    }

    private Coordinates getCoordinatesFromOption(String option){
        String[] coords = option.split(",");

        double lat = Double.parseDouble(coords[0]);
        double lon = Double.parseDouble(coords[1]);
        return new Coordinates(lat, lon);
    }


    @FXML
    private void handleButtonGetForecastWeather() throws IOException {
        List<PredictionWeather> listPredictionWeather;
        message.setText("");
        errorMessage.setText("");
        String option = favourites.getSelectionModel().getSelectedItem();

        try{


            Coordinates coordinates = getCoordinatesFromOption(option);
            listPredictionWeather = helperControllerMain.getPredictionWeatherOfCoordinates(coordinates);
            helperControllerMain.showPredictionWeather(listPredictionWeather);
        }catch (Exception e){
            System.out.println("es una ciudad");
            try {
                listPredictionWeather = helperControllerMain.getPredictionWeatherOfCity(option);
                helperControllerMain.showPredictionWeather(listPredictionWeather);

            }catch (Exception ex){
                helperControllerMain.showErrorCityOrCoordinates();
            }
        }



    }


    @FXML
    private void handleAddNewFavourite() throws IOException {

        boolean isCreated = false;
        message.setText("");
        errorMessage.setText("");
        String added = "";

        try{
            if (type.getSelectionModel().getSelectedItem().toUpperCase().equals("COORDINATES")){

                Coordinates coordinates = getCoordinatesFromOption(coordinatesCity.getText());

                isCreated = helperControllerMain.addNewFavouriteCoordinate(coordinates);
                added = coordinates.toString();

            }else{

                added = coordinatesCity.getText();
                isCreated = helperControllerMain.addNewFavouriteCity(added);

            }
        }catch (Exception e){
            errorMessage.setText("Something went wrong");
            System.out.println("sucedió un error");
        }

        if (!isCreated) {
            errorMessage.setText("Something went wrong");
        }else{
            favourites.getItems().add(added);
            message.setText("New favourite place added correctly");
        }

    }



    @FXML
    private void handleDeleteFavourite() throws IOException {

        message.setText("");
        errorMessage.setText("");
        String option = favourites.getSelectionModel().getSelectedItem();
        boolean deleted = false;

        try{

            Coordinates coordinates = getCoordinatesFromOption(option);
            deleted = helperControllerMain.removeFavouriteCoordinates(coordinates);

        }catch (Exception e) {
            deleted = helperControllerMain.removeFavouriteCity(option);
        }

        if (!deleted) {
            errorMessage.setText("Something went wrong");
        }else{
            favourites.getItems().remove(option);
            message.setText("Favourite place deleted correctly");
        }


    }



}
