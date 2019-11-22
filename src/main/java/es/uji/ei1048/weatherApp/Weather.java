package es.uji.ei1048.weatherApp;

import java.time.LocalDateTime;

public abstract class Weather {
    double temperature;
    double humidty;
    double preassure;
    LocalDateTime dateOfConsultation;



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
