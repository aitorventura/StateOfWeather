package es.uji.ei1048.weatherApp;

import java.time.LocalDateTime;

public class PredictionWeather extends Weather{

    LocalDateTime predictionDate;


    public LocalDateTime getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(LocalDateTime predictionDate) {
        this.predictionDate = predictionDate;
    }

}
