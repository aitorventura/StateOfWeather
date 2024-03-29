package es.uji.ei1048.weatherApp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
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
            System.out.println(Double.parseDouble(coordMap.get("lon").toString()));

            currentWeather.setCoordinates(coordinates);
            currentWeather.setTemperature(Double.parseDouble(mainMap.get("temp").toString()));
            currentWeather.setMaxTemperature(Double.parseDouble(mainMap.get("temp_max").toString()));
            currentWeather.setMinTemperature(Double.parseDouble(mainMap.get("temp_min").toString()));
            currentWeather.setHumidty(Double.parseDouble(mainMap.get("humidity").toString()));
            currentWeather.setPressure(Double.parseDouble(mainMap.get("pressure").toString()));
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
        TreeMap<String, List<PredictionWeather>> temperaturesPerDay = new TreeMap<>();


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

            String name = "";

            try {

                name = nameJson.toString().substring(1, nameJson.toString().length() - 1);
            } catch (Exception e){
                name = "";
            }

            DecimalFormat df = new DecimalFormat("#.00");
            for (int i = 0; i < lista.size(); i++) {

                PredictionWeather p = new PredictionWeather();

                double longi = new BigDecimal(Double.parseDouble(longitude.toString())).setScale(2, RoundingMode.HALF_UP).doubleValue();
                double lati = new BigDecimal(Double.parseDouble(latitude.toString())).setScale(2, RoundingMode.HALF_UP).doubleValue();




                p.setCoordinates(new Coordinates(longi, lati));

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
                p.setPressure(Double.parseDouble(dataPressure.toString()));
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
            System.out.println("esto es el error del open weather");
            System.out.println(e.getMessage());
        }


        return giveMeOnlyThePredictionsOf3Days(temperaturesPerDay);
    }
    private List<PredictionWeather> getPredictionWeathers(String urlStringLocation, double lon, double lat) {
        TreeMap<String, List<PredictionWeather>> temperaturesPerDay = new TreeMap<>();


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

            String name = "";

            try {
                 name = nameJson.toString().substring(1, nameJson.toString().length() - 1);
            } catch (Exception e){
                 name = "";
            }

            for (int i = 0; i < lista.size(); i++) {

                PredictionWeather p = new PredictionWeather();

                if(name.equals("") || longitude == null || latitude == null){

                    p.setCoordinates(new Coordinates(lon, lat));

                }else {
                    p.setCoordinates(new Coordinates(Double.parseDouble(longitude.toString()), Double.parseDouble(latitude.toString())));
                }

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
                p.setPressure(Double.parseDouble(dataPressure.toString()));
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
        String urlStringCoordinates = "https://api.openweathermap.org/data/2.5/forecast?lon=" + lon + "&lat=" + lat + "&APPID=" + API_KEY + "&units=metric";

        return getPredictionWeathers(urlStringCoordinates, lon, lat);


    }

    @Override
    public void openWeatherMap(Coordinates coordinates, String typeOfMap) {

    }

    private List<PredictionWeather> giveMeOnlyThePredictionsOf3Days(TreeMap<String, List<PredictionWeather>> mapPredictionWeather){
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
                    sumPressure += p.getPressure();
                    sumHumidity += p.getHumidty();
                }

                PredictionWeather first = listPredictionsOfThisDay.get(0);

                predictionWeather.setCity(first.getCity());
                predictionWeather.setCoordinates(first.getCoordinates());
                predictionWeather.setPredictionDate(first.getPredictionDate());
                predictionWeather.setTemperature(sumTemperature/listPredictionsOfThisDay.size());
                predictionWeather.setPressure(sumPressure/listPredictionsOfThisDay.size());
                predictionWeather.setHumidty(sumHumidity/listPredictionsOfThisDay.size());

                finalListPredictions.add(predictionWeather);
                counterOfDays++;

            }

        }


        return finalListPredictions;
    }

}
