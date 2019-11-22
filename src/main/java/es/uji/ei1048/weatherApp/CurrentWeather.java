package es.uji.ei1048.weatherApp;

import java.time.LocalDateTime;

public class CurrentWeather extends Weather {
    double minTemperature;
    double maxTemperature;


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


}
