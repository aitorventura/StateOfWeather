package es.uji.ei1048.weatherApp.view;

import es.uji.ei1048.weatherApp.model.CurrentWeather;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class CurrentWeatherControllerView {

    //private CurrentWeather currentWeather;

    @FXML
    Text textCity;

    @FXML
    Text textCoordinates;

    @FXML
    Text textTemp;

    @FXML
    Text textMaxTemp;

    @FXML
    Text textMinTemp;

    @FXML
    Text textPressure;

    @FXML
    Text textHumidity;



    //@FXML
  //  Text textDataCurrentWeather;

    public void setCurrentWeather(CurrentWeather currentWeather) {
      //  this.currentWeather = currentWeather;
        try {
            this.textCity.setText(currentWeather.getCity());
            this.textCoordinates.setText(currentWeather.getCoordinates().getLon() + ", " + currentWeather.getCoordinates().getLat());
            this.textTemp.setText(currentWeather.getTemperature() +" ºC");
            this.textMaxTemp.setText(currentWeather.getMaxTemperature() +" ºC");
            this.textMinTemp.setText(currentWeather.getMinTemperature() +" ºC");
            this.textHumidity.setText(currentWeather.getHumidty() + " %");
            this.textPressure.setText(currentWeather.getPressure() + " hPa");
        } catch (Exception e){

        }






    }
/*
    public void setTextCity() {
        try{
            this.textDataCurrentWeather.setText("Current Weather from "+currentWeather.getCity());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setTextDataCurrentWeather() {

        try{
            this.textDataCurrentWeather.setText(currentWeather.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
*/
}
