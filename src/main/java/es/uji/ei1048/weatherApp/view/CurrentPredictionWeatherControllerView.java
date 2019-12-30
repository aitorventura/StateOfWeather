package es.uji.ei1048.weatherApp.view;

import es.uji.ei1048.weatherApp.main.HelperControllerMain;
import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;


public class CurrentPredictionWeatherControllerView {

    private HelperControllerMain helperControllerMain;

    @FXML
    TextField place;

    @FXML
    ChoiceBox<String> typeOfSearch;

    @FXML
    Button buttonCurrentWeather;

    @FXML
    Button buttonPredictionWeather;

    @FXML
    private void initialize() {
        typeOfSearch.getItems().add("Coordinates");
        typeOfSearch.getItems().add("City");
    }

    public void setHelperControllerMain(HelperControllerMain helperControllerMain){
        this.helperControllerMain = helperControllerMain;
    }

    @FXML
    private void handleButtonCurrentWeather() throws IOException {

        System.out.println("He apretado el botón de current weather");
        //boolean hasError = false;
        CurrentWeather currentWeather = null;

        //comprobar si son coodenadas
        try{
            if (typeOfSearch.getSelectionModel().getSelectedItem().toUpperCase().equals("COORDINATES")){

                String[] coords = place.getText().split(",");

                double lat = Double.parseDouble(coords[0]);
                double lon = Double.parseDouble(coords[1]);
                Coordinates coordinates = new Coordinates(lat, lon);

                currentWeather = helperControllerMain.getCurrentWeatherOfCoordinates(coordinates);
            }else{
                String city = place.getText();

                if (city == null){
                    throw new Exception(); //TODO cambiar a una excepción más apropiada
                }

                currentWeather = helperControllerMain.getCurrentWeatherOfCity(city);

            }
        }catch (Exception e){
            //TODO imprimir por pantalla error al intentar crear una coordenada
            //hasError = true;
            System.out.println("sucedió un error");
        }

        if (currentWeather != null){
            helperControllerMain.showCurrentWeather(currentWeather);
        }else{
            //TODO enseñar mensaje de que no se ha podido encontrar la ciudad/coordenada especificada
            //se debe tener en cuenta que al conseguir el tiempo se pueden lanzar excepciones
            System.out.println("sucedió un error");
        }

    }

    @FXML
    private void handleButtonPredictionWeather() throws IOException {
        //TODO implementar
    }
}
