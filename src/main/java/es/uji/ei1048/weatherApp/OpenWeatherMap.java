package es.uji.ei1048.weatherApp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.*;
import es.uji.ei1048.weatherApp.exceptions.NoDataInTheDatabaseAndOfflineException;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;

public class OpenWeatherMap implements IWeatherService {

    private String API_KEY = "142886e6af3947dd437c5dc91db51abb";


    public static Map<String, Object> jsonToMap(String str) {

        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        return map;
    }


    public CurrentWeather giveMeTheCurrentWeatherUsingACity(String city) {
        String urlStringLocation = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=" + API_KEY + "&units=metric";
        CurrentWeather currentWeather = getCurrentWeather(urlStringLocation);

        return currentWeather;

    }

    public CurrentWeather giveMeTheCurrentWeatherUsingCoordinates(double lon, double lat) {

        String urlStringCoordenadas = "http://api.openweathermap.org/data/2.5/weather?lon=" + lon + "&lat=" + lat + "&appid=" + API_KEY + "&units=metric";
        CurrentWeather currentWeather = getCurrentWeather(urlStringCoordenadas);

        return currentWeather;

    }

    private CurrentWeather getCurrentWeather(String urlFinished) {

        CurrentWeather currentWeather = new CurrentWeather();
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlFinished);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
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


        } catch (IOException e) {
            System.out.println(e.getMessage());
            currentWeather = null;
            //throw new NoDataInTheDatabaseAndOfflineException();
        }
        return currentWeather;
    }

    public List<PredictionWeather> giveMeTheListOfPredictionsUsingACity(String city) {

        String urlStringLocation = "https://api.openweathermap.org/data/2.5/forecast?q=" + city + "&APPID=" + API_KEY + "&units=metric";

        return getPredictionWeathers(urlStringLocation);


    }

    private List<PredictionWeather> getPredictionWeathers(String urlStringLocation) {
        HashMap<String, List<PredictionWeather>> temperaturesPerDay = new HashMap<>();


        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlStringLocation);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            rd.close();

            JsonParser parser = new JsonParser();


            Object obj = parser.parse(result.toString());
            JsonObject jsonObject = (JsonObject) obj;

            JsonArray lista = (JsonArray) jsonObject.get("list");


            JsonElement cityInfo = (JsonElement) jsonObject.get("city");
            Object objcity = parser.parse(cityInfo.toString());
            JsonObject cityData = (JsonObject) objcity;

            JsonElement nameJson = (JsonElement) cityData.get("name");

            JsonElement coordinates = (JsonElement) cityData.get("coord");

            Object coordObj = parser.parse(coordinates.toString());
            JsonObject coordinatesObject = (JsonObject) coordObj;
            JsonElement longitude = coordinatesObject.get("lon");
            JsonElement latitude = coordinatesObject.get("lat");
            String name = nameJson.toString().substring(1, nameJson.toString().length() - 1);


            for (int i = 0; i < lista.size(); i++) {
                PredictionWeather p = new PredictionWeather();
                p.setCoordinates(new Coordinates(Double.parseDouble(longitude.toString()), Double.parseDouble(latitude.toString())));
                p.setCity(name);

                JsonElement object = lista.get(i);


                Object obj2 = parser.parse(object.toString());
                JsonObject jsonObject2 = (JsonObject) obj2;


                JsonElement mainDeCadaLista = (JsonElement) jsonObject2.get("main");

                Object obj3 = parser.parse(mainDeCadaLista.toString());
                JsonObject jsonObject3 = (JsonObject) obj3;

                JsonElement fechaDeCadaLista = (JsonElement) jsonObject2.get("dt_txt");

                JsonElement dataTemperature = (JsonElement) jsonObject3.get("temp");
                JsonElement dataPressure = (JsonElement) jsonObject3.get("pressure");
                JsonElement dataHumidity = (JsonElement) jsonObject3.get("humidity");

                String date = fechaDeCadaLista.toString().substring(1, fechaDeCadaLista.toString().length() - 1);

                p.setPredictionDate(Timestamp.valueOf(date));
                p.setHumidty(Double.parseDouble(dataHumidity.toString()));
                p.setPreassure(Double.parseDouble(dataPressure.toString()));
                p.setTemperature(Double.parseDouble(dataTemperature.toString()));

                String[] dateSplit = date.split(" ");
                String day = dateSplit[0];

                if (!temperaturesPerDay.containsKey(day)) {
                    List<PredictionWeather> predictionWeathers = new ArrayList<PredictionWeather>();
                    predictionWeathers.add(p);
                    temperaturesPerDay.put(day, predictionWeathers);
                } else {
                    List<PredictionWeather> predictionWeathers = temperaturesPerDay.get(day);
                    predictionWeathers.add(p);
                }
            }


        } catch(IOException e)    {
            System.out.println(e.getMessage());
        }


        return giveMeOnlyThePredictionsOf3Days(temperaturesPerDay);
    }

    public List<PredictionWeather> giveMeTheListOfPredictionsUsingCoordinates(double lon, double lat){
        String urlStringCoordinates = "https://api.openweathermap.org/data/2.5/forecast?lon=" + lat + "&lat=" + lon + "&APPID=" + API_KEY + "&units=metric";

        return getPredictionWeathers(urlStringCoordinates);


    }

    @Override
    public void openWeatherMap(Coordinates coordinates, String typeOfMap) {

    }

    private List<PredictionWeather> giveMeOnlyThePredictionsOf3Days(HashMap<String, List<PredictionWeather>> mapPredictionWeather){
        List<PredictionWeather> finalListPredictions = new ArrayList<>();

        if(mapPredictionWeather == null){
            return null;
        }

        String today = LocalDate.now().toString();

        int counterOfDays = 0;
        for(String day : mapPredictionWeather.keySet()){
            if(!day.equals(today) && counterOfDays < 3){
                List<PredictionWeather> listPredictionsOfThisDay = mapPredictionWeather.get(day);
                PredictionWeather predictionWeather  = new PredictionWeather();

                double sumTemperature = 0;
                double sumPressure = 0;
                double sumHumidity = 0;

                for(PredictionWeather p : listPredictionsOfThisDay){
                    sumTemperature += p.getTemperature();
                    sumPressure += p.getPreassure();
                    sumHumidity += p.getHumidty();
                }

                PredictionWeather first = listPredictionsOfThisDay.get(0);

                predictionWeather.setCity(first.getCity());
                predictionWeather.setCoordinates(first.getCoordinates());
                predictionWeather.setPredictionDate(first.getPredictionDate());
                predictionWeather.setTemperature(sumTemperature/listPredictionsOfThisDay.size());
                predictionWeather.setPreassure(sumPressure/listPredictionsOfThisDay.size());
                predictionWeather.setHumidty(sumHumidity/listPredictionsOfThisDay.size());

                finalListPredictions.add(predictionWeather);
                counterOfDays++;

            }

        }
        return finalListPredictions;
    }

}
