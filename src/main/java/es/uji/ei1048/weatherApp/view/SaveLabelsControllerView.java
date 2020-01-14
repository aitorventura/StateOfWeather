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
import java.util.Set;


public class SaveLabelsControllerView {



    HelperControllerMain helperControllerMain;

    @FXML
    TextField nameOfTheLabel;

    @FXML
    TextField coordinatesOfTheLabel;

    @FXML
    ChoiceBox<String> savedLabelsOptions;

    @FXML
    Button weatherOfLabel;

    @FXML
    Button createLabel;

    @FXML
    Text message;

    @FXML
     Text errorMessage;

    public void setHelperControllerMain(HelperControllerMain helperControllerMain){
        this.helperControllerMain = helperControllerMain;

        Set<String> listLabels = helperControllerMain.getLabels();

        for(String label: listLabels){
            savedLabelsOptions.getItems().add(label);
        }

    }

    @FXML
    private void handleButtonGetWeather() throws IOException {

        message.setText("");
        errorMessage.setText("");
       String option = savedLabelsOptions.getSelectionModel().getSelectedItem();

       if(option == null || option.equals("")){
           helperControllerMain.showErrorCityOrCoordinates();
       } else {
           try {
               CurrentWeather currentWeather = helperControllerMain.getCurrentWeatherUsingLabel(option);
               helperControllerMain.showCurrentWeather(currentWeather);
           }catch (Exception e){
               errorMessage.setText("No connection");
           }
       }

    }

    @FXML
    private void handleAddNewLabel() throws IOException {
        message.setText("");
        errorMessage.setText("");
        try {
            String[] coords = coordinatesOfTheLabel.getText().split(",");

            double lat = Double.parseDouble(coords[0]);
            double lon = Double.parseDouble(coords[1]);
            Coordinates coordinates = new Coordinates(lat, lon);

            if(nameOfTheLabel.getText() == null || nameOfTheLabel.getText().equals("")){
                errorMessage.setText("Please, fill all the gaps correctly");
            } else {
                boolean result = helperControllerMain.addNewLabel(nameOfTheLabel.getText(), coordinates);

                if(result){
                    message.setText("Label added correctly");
                    savedLabelsOptions.getItems().add(nameOfTheLabel.getText());
                } else {
                    errorMessage.setText("Error, this label is already created");
                }
            }
        } catch (Exception e){
            errorMessage.setText("Please, fill all the gaps correctly");
        }
    }



    @FXML
    private void handleDeleteLabel() throws IOException {

        message.setText("");
        errorMessage.setText("");
        String option = savedLabelsOptions.getSelectionModel().getSelectedItem();

        if(option == null || option.equals("")){
            //Error
        } else {
            savedLabelsOptions.getItems().remove(option);
            helperControllerMain.removeLabel(option);
        }

    }




    }
