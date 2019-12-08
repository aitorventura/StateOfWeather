package es.uji.ei1048.weatherApp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PredictionWeather extends Weather{

    Timestamp predictionDate;


    public Timestamp getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(Timestamp predictionDate) {
        this.predictionDate = predictionDate;
    }

}
