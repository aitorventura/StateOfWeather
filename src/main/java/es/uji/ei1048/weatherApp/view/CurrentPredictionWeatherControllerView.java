package es.uji.ei1048.weatherApp.view;

import es.uji.ei1048.weatherApp.main.HelperControllerMain;
import es.uji.ei1048.weatherApp.model.Coordinates;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.model.PredictionWeather;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.util.List;


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
                System.out.println(currentWeather.toString());
            }else{
                String city = place.getText();

                if (city == null){
                    throw new Exception(); //TODO cambiar a una excepción más apropiada
                }

                currentWeather = helperControllerMain.getCurrentWeatherOfCity(city);
                System.out.println(currentWeather.toString());


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
        System.out.println("He apretado el botón de prediction weather");
        //boolean hasError = false;
        List<PredictionWeather> predictionWeatherList = null;

        //comprobar si son coodenadas
        try{
            if (typeOfSearch.getSelectionModel().getSelectedItem().toUpperCase().equals("COORDINATES")){

                String[] coords = place.getText().split(",");

                double lat = Double.parseDouble(coords[0]);
                double lon = Double.parseDouble(coords[1]);
                Coordinates coordinates = new Coordinates(lat, lon);

                predictionWeatherList = helperControllerMain.getPredictionWeatherOfCoordinates(coordinates);
            }else{
                String city = place.getText();

                if (city == null){
                    throw new Exception(); //TODO cambiar a una excepción más apropiada
                }

                predictionWeatherList = helperControllerMain.getPredictionWeatherOfCity(city);

            }
        }catch (Exception e){
            //TODO imprimir por pantalla error al intentar crear una coordenada
            //hasError = true;
            System.out.println("sucedió un error");
        }

        if (predictionWeatherList != null){
            helperControllerMain.showPredictionWeather(predictionWeatherList);
        }else{
            //TODO enseñar mensaje de que no se ha podido encontrar la ciudad/coordenada especificada
            //se debe tener en cuenta que al conseguir el tiempo se pueden lanzar excepciones
            System.out.println("sucedió un error");
        }
    }
}
