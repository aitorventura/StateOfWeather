package es.uji.ei1048.weatherApp.view;

import es.uji.ei1048.weatherApp.model.CurrentWeather;
import javafx.fxml.FXML;

import javax.xml.soap.Text;

public class CurrentWeatherControllerView {

    private CurrentWeather currentWeather;

    @FXML
    Text textCity;

    @FXML
    Text textDataCurrentWeather;

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public void setTextCity() {
        try{
            this.textDataCurrentWeather.setValue("Current Weather from "+currentWeather.getCity());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setTextDataCurrentWeather() {

        try{
            this.textDataCurrentWeather.setValue(currentWeather.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
