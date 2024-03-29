package es.uji.ei1048.weatherApp.main;
import es.uji.ei1048.weatherApp.model.CurrentWeather;
import es.uji.ei1048.weatherApp.model.PredictionWeather;
import es.uji.ei1048.weatherApp.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainApp extends Application{

    private Stage primaryStage;
    private Stage currentPredictionWeatherStage;
    private Stage currentWeatherStage;
    private Stage savedLabelsStage;
    private Stage predictionWeatherStage;
    private Stage errorWeatherStage;
    private Stage favouritesStage;
    private Stage openAMapStage;


    private AnchorPane mainAppPane;
    private AnchorPane currentPredicitionWeatherPane;
    private AnchorPane currentWeatherPane;
    private AnchorPane savedLabelsPane;
    private AnchorPane predictionWeatherPane;
    private AnchorPane errorWeatherPane;
    private AnchorPane favouritesPane;
    private AnchorPane openAMapPane;




    private MainControllerView controllerView;
    private HelperControllerMain helperControllerMain;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        helperControllerMain = new HelperControllerMain(this);

        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setTitle("State of Weather Application");
        initMainPane();

    }


    public void initMainPane() {

        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();

            /* A diferencia de Eclipse, por la estructura de ficheros es necesario añadir ../
             * para especificar correctamente la ubicación de la pantalla de inicio
             */
            loader.setLocation(MainApp.class.getResource("../view/MainView.fxml"));

            mainAppPane = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(mainAppPane);
            primaryStage.setScene(scene);

            // Give the controllerView access to the main app.
            controllerView = loader.getController();
            controllerView.setHelperControllerMain(helperControllerMain);

        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.show();

    }

    public void closeMainAppView() {
        primaryStage.close();
    }

    public void initCurrentPredictionWeather(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/CurrentPredictionWeatherView.fxml"));
            currentPredicitionWeatherPane = (AnchorPane) loader.load();

            currentPredictionWeatherStage = new Stage();
            currentPredictionWeatherStage.setTitle("CurrentPredictionWeatherView");
            currentPredictionWeatherStage.initOwner(primaryStage);
            currentPredictionWeatherStage.setResizable(false);
            Scene scene = new Scene(currentPredicitionWeatherPane);
            currentPredictionWeatherStage.setScene(scene);

            CurrentPredictionWeatherControllerView controller = loader.getController();
            controller.setHelperControllerMain(helperControllerMain);

            // Show the dialog and wait until the user closes it
            //currentPredictionWeatherStage.showAndWait();
            currentPredictionWeatherStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeCurrentPredictionWeatherView() {
        currentPredictionWeatherStage.close();
    }

    public void initPrintCurrentWeather(CurrentWeather currentWeather){

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/CurrentWeatherView.fxml"));
            currentWeatherPane = (AnchorPane) loader.load();
            currentWeatherStage = new Stage();
            currentWeatherStage.setTitle("CurrentWeatherView");
            currentWeatherStage.initOwner(primaryStage);
            currentWeatherStage.setResizable(false);
            Scene scene = new Scene(currentWeatherPane);
            currentWeatherStage.setScene(scene);

            CurrentWeatherControllerView controller = loader.getController();
            controller.setCurrentWeather(currentWeather);
            //controller.setTextDataCurrentWeather();

            // Show the dialog and wait until the user closes it
            currentWeatherStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeCurrentWeatherView() {
        currentWeatherStage.close();
    }

    public void initSavedLabelsView(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/SaveLabelsView.fxml"));
            savedLabelsPane = (AnchorPane) loader.load();
            savedLabelsStage = new Stage();
            savedLabelsStage.setTitle("SavedLabelsView");
            savedLabelsStage.initOwner(primaryStage);
            savedLabelsStage.setResizable(false);
            Scene scene = new Scene(savedLabelsPane);
            savedLabelsStage.setScene(scene);

            SaveLabelsControllerView saveLabelsControllerView = loader.getController();
            saveLabelsControllerView.setHelperControllerMain(helperControllerMain);

            // Show the dialog and wait until the user closes it
            savedLabelsStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initFavouritesView(){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/FavoritesView.fxml"));
            favouritesPane = (AnchorPane) loader.load();
            favouritesStage = new Stage();
            favouritesStage.setTitle("FavouritesView");
            favouritesStage.initOwner(primaryStage);
            favouritesStage.setResizable(false);
            Scene scene = new Scene(favouritesPane);
            favouritesStage.setScene(scene);

            FavoritesViewController favoritesViewController = loader.getController();
            favoritesViewController.setHelperControllerMain(helperControllerMain);

            // Show the dialog and wait until the user closes it
            favouritesStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPrintPredictionWeather(List<PredictionWeather> predictionWeatherList){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/PredictionWeatherView.fxml"));
            predictionWeatherPane = (AnchorPane) loader.load();
            predictionWeatherStage = new Stage();
            predictionWeatherStage.setTitle("PredictionWeatherView");
            predictionWeatherStage.initOwner(primaryStage);
            predictionWeatherStage.setResizable(false);
            Scene scene = new Scene(predictionWeatherPane);
            predictionWeatherStage.setScene(scene);

            PredictionWeatherControllerView controller = loader.getController();
            controller.setListPredictionWeather(predictionWeatherList);
            //controller.setTextDataCurrentWeather();

            // Show the dialog and wait until the user closes it
            predictionWeatherStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initErrorCityOrCoordinates(){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/ErrorWeatherView.fxml"));

            errorWeatherPane = (AnchorPane) loader.load();

            errorWeatherStage = new Stage();
            errorWeatherStage.setTitle("Error");
            errorWeatherStage.initOwner(primaryStage);
            errorWeatherStage.setResizable(false);
            Scene scene = new Scene(errorWeatherPane);
            errorWeatherStage.setScene(scene);

            // Show the dialog and wait until the user closes it
            //currentPredictionWeatherStage.showAndWait();
            errorWeatherStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initOpenAMap() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("../view/OpenAMapView.fxml"));
            openAMapPane = (AnchorPane) loader.load();
            openAMapStage = new Stage();
            openAMapStage.setTitle("OpenAMapView");
            openAMapStage.initOwner(primaryStage);
            openAMapStage.setResizable(false);
            Scene scene = new Scene(openAMapPane);
            openAMapStage.setScene(scene);

            OpenAMapViewController controller = loader.getController();
            controller.setHelperControllerMain(helperControllerMain);

            // Show the dialog and wait until the user closes it
            openAMapStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
