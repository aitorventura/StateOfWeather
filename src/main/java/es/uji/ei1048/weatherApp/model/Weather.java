package es.uji.ei1048.weatherApp.model;

public abstract class Weather {

    String city;
    Coordinates coordinates;
    double temperature;
    double humidty;
    double pressure;



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

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double preassure) {
        this.pressure = preassure;
    }

}
