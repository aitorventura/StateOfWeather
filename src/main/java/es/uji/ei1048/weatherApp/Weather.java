package es.uji.ei1048.weatherApp;

import java.time.LocalDateTime;

public abstract class Weather {
    String city;
    double longitude;
    double latitude;
    double temperature;
    double humidty;
    double preassure;
    LocalDateTime dateOfConsultation;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    public LocalDateTime getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(LocalDateTime dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }

}
