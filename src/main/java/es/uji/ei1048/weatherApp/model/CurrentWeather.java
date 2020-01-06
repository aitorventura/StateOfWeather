package es.uji.ei1048.weatherApp.model;

import java.sql.Timestamp;

public class CurrentWeather extends Weather {

    double minTemperature;
    double maxTemperature;
    Timestamp dateOfConsultation;


    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Timestamp getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(Timestamp dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }

    @Override
    public String toString() {
        return "CurrentWeather{" +
                "minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", dateOfConsultation=" + dateOfConsultation +
                ", city='" + city + '\'' +
                ", coordinates=" + coordinates +
                ", temperature=" + temperature +
                ", humidty=" + humidty +
                ", preassure=" + pressure +
                '}';
    }
}
