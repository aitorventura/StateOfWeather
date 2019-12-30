package es.uji.ei1048.weatherApp.model;

import java.sql.Timestamp;

public class PredictionWeather extends Weather{

    Timestamp predictionDate;


    public Timestamp getPredictionDate() {
        return predictionDate;
    }

    public void setPredictionDate(Timestamp predictionDate) {
        this.predictionDate = predictionDate;
    }

    @Override
    public String toString() {
        return "PredictionWeather{" +
                "predictionDate=" + predictionDate +
                ", city='" + city + '\'' +
                ", coordinates=" + coordinates +
                ", temperature=" + temperature +
                ", humidty=" + humidty +
                ", preassure=" + preassure +
                '}';
    }
}
