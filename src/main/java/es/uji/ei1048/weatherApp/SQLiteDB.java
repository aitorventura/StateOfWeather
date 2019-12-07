package es.uji.ei1048.weatherApp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;

public class SQLiteDB {
    Connection c = null;
    Statement stmt = null;

    public SQLiteDB(){
         //try connect to DB
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:StateOfWeatherDataBase.db");
        } catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }
    }

    //CONSULTATIONS TO ADMIN CURRENT WEATHER

    public void removeOldCurrentTimes(){
        try{
            this.stmt = c.createStatement();
            stmt.execute("DELETE FROM CurrentWeather " +
                             "WHERE city IN (SELECT city " +
                                              "FROM CurrentWeather " +
                                                "WHERE ((julianday('now') - julianday( dateOfConsultation)) * 24 * 60) > 60)");


        } catch (Exception e){

        }

    }

    public CurrentWeather giveMeTheCurrentWeather(String city){
        CurrentWeather currentWeather = new CurrentWeather();
        try{
            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM CurrentWeather WHERE city = '"+ city + "'");

                currentWeather.setCity(city);
                Coordinates coordinates = new Coordinates(resultSet.getDouble("longitude"), resultSet.getDouble("latitude"));
                currentWeather.setCoordinates(coordinates);
                currentWeather.setTemperature(resultSet.getDouble("temperature"));
                currentWeather.setMaxTemperature(resultSet.getDouble("maxTemperature"));
                currentWeather.setMinTemperature(resultSet.getDouble("minTemperature"));
                currentWeather.setHumidty(resultSet.getDouble("humidity"));
                currentWeather.setPreassure(resultSet.getDouble("pressure"));

                StringBuilder s = new StringBuilder(resultSet.getString("dateOfConsultation"));
                s.append(".00");
                 Timestamp ts = Timestamp.valueOf(s.toString());
                currentWeather.setDateOfConsultation(ts);

                return currentWeather;

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


    }


    public CurrentWeather giveMeTheCurrentWeather(double lon, double lat){

        BigDecimal newLon = new BigDecimal(lon);
        newLon = newLon.setScale(2, RoundingMode.DOWN);

        BigDecimal newLat = new BigDecimal(lat);
        newLat = newLat.setScale(2, RoundingMode.DOWN);


        CurrentWeather currentWeather = new CurrentWeather();
        try{
            this.stmt = c.createStatement();

            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM CurrentWeather WHERE longitude = ");
            query.append(newLon);
            query.append(" AND latitude = ");
            query.append(newLat);

            ResultSet resultSet = stmt.executeQuery(query.toString());




            currentWeather.setCity(resultSet.getString("city"));
            Coordinates coordinates = new Coordinates(resultSet.getDouble("longitude"), resultSet.getDouble("latitude"));
            currentWeather.setCoordinates(coordinates);
            currentWeather.setTemperature(resultSet.getDouble("temperature"));
            currentWeather.setMaxTemperature(resultSet.getDouble("maxTemperature"));
            currentWeather.setMinTemperature(resultSet.getDouble("minTemperature"));
            currentWeather.setHumidty(resultSet.getDouble("humidity"));
            currentWeather.setPreassure(resultSet.getDouble("pressure"));
            StringBuilder s = new StringBuilder(resultSet.getString("dateOfConsultation"));
            s.append(".00");
            Timestamp ts = Timestamp.valueOf(s.toString());
            currentWeather.setDateOfConsultation(ts);


            return currentWeather;


        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }


    public void addCurrentWeatherToTheDataBase(CurrentWeather currentWeather){
        try{
            this.stmt = c.createStatement();
            StringBuilder s = new StringBuilder();
            s.append("INSERT INTO CURRENTWEATHER VALUES('" + currentWeather.getCity()+ "',");
            s.append(currentWeather.getCoordinates().getLon() + ",");
            s.append(currentWeather.getCoordinates().getLat() + ",");
            s.append(currentWeather.getTemperature() + ",");
            s.append(currentWeather.getHumidty() + ",");
            s.append(currentWeather.getPreassure() + ",");
            s.append(currentWeather.getMinTemperature() + ",");
            s.append(currentWeather.getMaxTemperature() + ",");
            s.append("CURRENT_TIMESTAMP)");

            stmt.execute(s.toString());

        } catch (Exception e){

            e.printStackTrace();
        }


    }




    //CONSULTATIONS TO ADMIN FAVORITES
    public void listFavoriteCities(){
        try{
            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Favorite");

            while (resultSet.next()){
                String city = resultSet.getString("city");
                System.out.println(city);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addCityToFavorite(String city){
        try{
            this.stmt = c.createStatement();
            stmt.execute("INSERT INTO FAVORITE(city) VALUES('"+ city+ "')");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteCityFromFavorite(String city){
        try{
            this.stmt = c.createStatement();
            stmt.execute("DELETE FROM FAVORITE WHERE CITY = '"+ city+ "'");


        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void closeConnection(){
        try{
            c.close();

        } catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }




    }

}
