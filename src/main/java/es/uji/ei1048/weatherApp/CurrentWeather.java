package es.uji.ei1048.weatherApp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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


}
