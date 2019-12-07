package es.uji.ei1048.weatherApp;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.*;

public class OpenWeatherMap {

    private String API_KEY = "142886e6af3947dd437c5dc91db51abb";


    public static Map<String, Object> jsonToMap(String str){

        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());

        return map;
    }


    public CurrentWeather giveMeTheCurrentWeatherUsingACity(String city){
        String urlStringLocation ="http://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=" + API_KEY + "&units=metric";
        CurrentWeather currentWeather = getCurrentWeather(urlStringLocation);

        return currentWeather;

    }

    public CurrentWeather giveMeTheCurrentWeatherUsingCoordinates(double lon, double lat){

        String urlStringCoordenadas = "http://api.openweathermap.org/data/2.5/weather?lon=" + lon +"&lat=" + lat +"&appid=" + API_KEY + "&units=metric";
        CurrentWeather currentWeather = getCurrentWeather(urlStringCoordenadas);

        return currentWeather;

    }

    private CurrentWeather getCurrentWeather( String urlFinished) {

        CurrentWeather currentWeather = new CurrentWeather();
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlFinished);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line = rd.readLine()) != null) {
                result.append(line);
            }

            rd.close();


            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> coordMap = jsonToMap(respMap.get("coord").toString());

            currentWeather.setCity(respMap.get("name").toString());
            Coordinates coordinates = new Coordinates(Double.parseDouble(coordMap.get("lon").toString()), Double.parseDouble(coordMap.get("lat").toString()));
            currentWeather.setCoordinates(coordinates);
            currentWeather.setTemperature(Double.parseDouble(mainMap.get("temp").toString()));
            currentWeather.setMaxTemperature(Double.parseDouble(mainMap.get("temp_max").toString()));
            currentWeather.setMinTemperature(Double.parseDouble(mainMap.get("temp_min").toString()));
            currentWeather.setHumidty(Double.parseDouble(mainMap.get("humidity").toString()));
            currentWeather.setPreassure(Double.parseDouble(mainMap.get("pressure").toString()));
            currentWeather.setDateOfConsultation(new Timestamp(System.currentTimeMillis()));


        } catch(IOException e) {
            System.out.println(e.getMessage());
            currentWeather = null;
        }
        return currentWeather;
    }


}
