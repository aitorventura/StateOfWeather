package es.uji.ei1048.weatherApp;

import es.uji.ei1048.weatherApp.controllerWeather.OpenAMap;

public class Main {
    public static void main(String[] args){
        double lon = -4.1968;
        double lat = 40.220478;

        //Zoom predeterminado para la página
        int zoom = 6;


        //Mostrar ciudades o no
        boolean ciudades = true;

        String url = "https://openweathermap.org/weathermap?basemap=map&cities=" + ciudades + "&layer=" + "temperature" + "&lat=" + lat + "&lon=" + lon + "&zoom=" + zoom;


        OpenAMap.goToURL(url);


    }
}
