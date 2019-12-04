package es.uji.ei1048.weatherApp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class Weather {
    String city;
    Coordinates coordinates;
    double temperature;
    double humidty;
    double preassure;
    Timestamp dateOfConsultation;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidty() {
        return humidty;
    }

    public void setHumidty(double humidty) {
        this.humidty = humidty;
    }

    public double getPreassure() {
        return preassure;
    }

    public void setPreassure(double preassure) {
        this.preassure = preassure;
    }

    public Timestamp getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(Timestamp dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }

}
