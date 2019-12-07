package es.uji.ei1048.weatherApp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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

    public void removeOldCurrentWeathers(){
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


    //CONSULTATIONS TO ADMIN FAVORITECITY

    public List<String> listFavoriteCities(){

        List<String> favouriteCities = new ArrayList<>();

        try{
            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM FavoriteCity");

            while (resultSet.next()){
                String city = resultSet.getString("city");
                favouriteCities.add(city);
                System.out.println(city);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return favouriteCities;
    }

    public boolean addCityToFavorite(String city){
        try{
            this.stmt = c.createStatement();
            stmt.execute("INSERT INTO FAVORITECITY(city) VALUES('"+ city+ "')");
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeCityFromFavorite(String city){
        //todo hay que comprobar si al intentar borrar algo que no existe da error, en teoría no deberia
        //por tanto, se debe ver si la ciudad estaba en la bbdd o no

        try{
            this.stmt = c.createStatement();
            stmt.execute("DELETE FROM FAVORITECITY WHERE CITY = '"+ city+ "'");
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //CONSULTATIONS TO ADMIN FAVORITECOORDINATES

    public List<Coordinates> listFavoriteCoordinates(){

        List<Coordinates> favouriteCoordinates = new ArrayList<>();

        try{
            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM FavoriteCoordinates");

            while (resultSet.next()){
                double longitude = resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");

                Coordinates coordinate = new Coordinates(longitude,latitude);
                favouriteCoordinates.add(coordinate);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return favouriteCoordinates;
    }

    public boolean addCoordinatesToFavorite(Coordinates coordinates){
        try{
            this.stmt = c.createStatement();
            StringBuilder s = new StringBuilder();
            s.append("INSERT INTO FAVORITECOORDINATES(longitude,latitude) VALUES(");
            s.append(coordinates.getLon()+",");
            s.append(coordinates.getLat()+")");

            stmt.execute(s.toString());
            return true;

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeCoordinatesFromFavorite(Coordinates coordinates){
        //todo hay que comprobar si al intentar borrar algo que no existe da error, en teoría no deberia
        //por tanto, se debe ver si la ciudad estaba en la bbdd o no

        try{
            this.stmt = c.createStatement();
            StringBuilder s = new StringBuilder();
            s.append("DELETE FROM FAVORITECOORDINATES WHERE longitude =");
            s.append(coordinates.getLon()+" AND latitude =");
            s.append(coordinates.getLat());
            stmt.execute(s.toString());
            return true;

        } catch (Exception e){
            //e.printStackTrace();
            return false;
        }
    }

    //CERRAR CONEXIÓN

    public void closeConnection(){
        try{
            c.close();

        } catch (Exception e){
            System.out.println("Error " + e.getMessage());
        }




    }

}
