package es.uji.ei1048.weatherApp.view;

import es.uji.ei1048.weatherApp.model.PredictionWeather;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.List;

public class PredictionWeatherControllerView {


    @FXML
    Text textCity;

    @FXML
    Text textCoordinates;

    @FXML
    Text textTemp1;

    @FXML
    Text textPressure1;

    @FXML
    Text textHumidity1;

    @FXML
    Text textTemp2;

    @FXML
    Text textPressure2;

    @FXML
    Text textHumidity2;

    @FXML
    Text textTemp3;

    @FXML
    Text textPressure3;

    @FXML
    Text textHumidity3;

    @FXML
    Text textDay1;

    @FXML
    Text textDay2;

    @FXML
    Text textDay3;


    public void setListPredictionWeather(List<PredictionWeather> predictionWeatherList){
        DecimalFormat format = new DecimalFormat("#.00");
        textCity.setText(predictionWeatherList.get(0).getCity());
        textCoordinates.setText(format.format(predictionWeatherList.get(0).getCoordinates().getLat()) + ", " + format.format(predictionWeatherList.get(0).getCoordinates().getLon()));

        textDay1.setText(predictionWeatherList.get(0).getPredictionDate().toString().split(" ")[0]);
        textPressure1.setText(format.format(predictionWeatherList.get(0).getPressure()) + " hPa");
        textHumidity1.setText(format.format(predictionWeatherList.get(0).getHumidty()) + "%");
        textTemp1.setText(format.format(predictionWeatherList.get(0).getTemperature()) + " ºC");

        textDay2.setText(predictionWeatherList.get(1).getPredictionDate().toString().split(" ")[0]);
        textPressure2.setText(format.format(predictionWeatherList.get(1).getPressure()) + " hPa");
        textHumidity2.setText(format.format(predictionWeatherList.get(1).getHumidty())+ "%");
        textTemp2.setText(format.format(predictionWeatherList.get(1).getTemperature())+ " ºC");

        textDay3.setText(predictionWeatherList.get(2).getPredictionDate().toString().split(" ")[0]);
        textPressure3.setText(format.format(predictionWeatherList.get(2).getPressure()) + " hPa");
        textHumidity3.setText(format.format(predictionWeatherList.get(2).getHumidty())+ "%");
        textTemp3.setText(format.format(predictionWeatherList.get(2).getTemperature()) + " ºC");

    }
}
