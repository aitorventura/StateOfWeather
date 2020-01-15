package es.uji.ei1048.weatherApp.model;

import es.uji.ei1048.weatherApp.exceptions.ThereAreNoFavouriteCities;
import es.uji.ei1048.weatherApp.interfaces.IStore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.*;

public class SQLiteDB implements IStore {
    Connection c = null;
    Statement stmt = null;

    public SQLiteDB() {
    }

    public void open() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:StateOfWeatherDataBase.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() throws Exception {
        c.close();

    }


    //CONSULTATIONS TO ADMIN CURRENT WEATHER

    public void removeOldCurrentWeathers() {
        try {
            this.open();
            this.stmt = c.createStatement();
            stmt.execute("DELETE FROM CurrentWeather " +
                    "WHERE dateOfConsultation IN (SELECT dateOfConsultation " +
                    "FROM CurrentWeather " +
                    "WHERE ((julianday('now') - julianday( dateOfConsultation)) * 24 * 60) > 60)");

            this.close();
        } catch (Exception e) {

        }


    }

    public CurrentWeather giveMeTheCurrentWeather(String city) {

        System.out.println(city);
        CurrentWeather currentWeather = new CurrentWeather();
        try {
            this.open();
            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM CurrentWeather WHERE city = '" + city + "'");

            currentWeather.setCity(city);
            Coordinates coordinates = new Coordinates(resultSet.getDouble("longitude"), resultSet.getDouble("latitude"));
            currentWeather.setCoordinates(coordinates);
            currentWeather.setTemperature(resultSet.getDouble("temperature"));
            currentWeather.setMaxTemperature(resultSet.getDouble("maxTemperature"));
            currentWeather.setMinTemperature(resultSet.getDouble("minTemperature"));
            currentWeather.setHumidty(resultSet.getDouble("humidity"));
            currentWeather.setPressure(resultSet.getDouble("pressure"));

            StringBuilder s = new StringBuilder(resultSet.getString("dateOfConsultation"));
            s.append(".00");
            Timestamp ts = Timestamp.valueOf(s.toString());
            currentWeather.setDateOfConsultation(ts);

            this.close();
            return currentWeather;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }


    }


    public CurrentWeather giveMeTheCurrentWeather(double lon, double lat) {

        BigDecimal newLon = new BigDecimal(lon);
        newLon = newLon.setScale(2, RoundingMode.DOWN);

        BigDecimal newLat = new BigDecimal(lat);
        newLat = newLat.setScale(2, RoundingMode.DOWN);


        CurrentWeather currentWeather = new CurrentWeather();

        try {
            this.open();

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
            currentWeather.setPressure(resultSet.getDouble("pressure"));
            StringBuilder s = new StringBuilder(resultSet.getString("dateOfConsultation"));
            s.append(".00");
            Timestamp ts = Timestamp.valueOf(s.toString());
            currentWeather.setDateOfConsultation(ts);

            this.close();

            return currentWeather;


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void addCurrentWeatherToTheDataBase(CurrentWeather currentWeather) {
        try {
            this.stmt = c.createStatement();
            StringBuilder s = new StringBuilder();
            s.append("INSERT INTO CURRENTWEATHER VALUES('" + currentWeather.getCity() + "',");
            s.append(currentWeather.getCoordinates().getLon() + ",");
            s.append(currentWeather.getCoordinates().getLat() + ",");
            s.append(currentWeather.getTemperature() + ",");
            s.append(currentWeather.getHumidty() + ",");
            s.append(currentWeather.getPressure() + ",");
            s.append(currentWeather.getMinTemperature() + ",");
            s.append(currentWeather.getMaxTemperature() + ",");
            s.append("CURRENT_TIMESTAMP)");

            stmt.execute(s.toString());

        } catch (Exception e) {

            //e.printStackTrace();
        }


    }


    //CONSULTATIONS TO ADMIN PREDICTION WEATHER

    public void removeAllPredictions() {
        try {
            this.open();

            this.stmt = c.createStatement();
            stmt.execute("DELETE FROM PredictionWeather");
            this.close();

        } catch (Exception e) {

        }
    }

    public void removeOldPredicionWeathers() {
        try {
            this.open();

            this.stmt = c.createStatement();
            stmt.execute("DELETE FROM PredictionWeather WHERE date IN (SELECT date FROM PredictionWeather WHERE ((julianday('now', 'start of day') - julianday(date , 'start of day')) ) >=  0)");
            this.close();

        } catch (Exception e) {

        }

    }

    public List<PredictionWeather> giveMeTheListOfPredictionWeather(String city) {


        PredictionWeather predictionWeather;
        try {
            this.open();
            this.stmt = c.createStatement();


            ResultSet resultSet = stmt.executeQuery("SELECT * FROM PredictionWeather WHERE city = '" + city + "'");
            ArrayList<PredictionWeather> list = new ArrayList<>();

            while (resultSet.next()) {
                predictionWeather = new PredictionWeather();
                predictionWeather.setCity(city);
                Coordinates coordinates = new Coordinates(resultSet.getDouble("longitude"), resultSet.getDouble("latitude"));
                predictionWeather.setCoordinates(coordinates);
                predictionWeather.setTemperature(resultSet.getDouble("temperature"));

                predictionWeather.setHumidty(resultSet.getDouble("humidity"));
                predictionWeather.setPressure(resultSet.getDouble("pressure"));

                StringBuilder s = new StringBuilder(resultSet.getString("date"));
                Timestamp ts = Timestamp.valueOf(s.toString());
                predictionWeather.setPredictionDate(ts);

                list.add(predictionWeather);

            }
            this.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public List<PredictionWeather> giveMeTheListOfPredictionWeatherUsingCoordinates(double lon, double lat) {

        BigDecimal newLon = new BigDecimal(lon);
        newLon = newLon.setScale(2, RoundingMode.DOWN);

        BigDecimal newLat = new BigDecimal(lat);
        newLat = newLat.setScale(2, RoundingMode.DOWN);

        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM PredictionWeather WHERE longitude = ");
        query.append(newLon);
        query.append(" AND latitude = ");
        query.append(newLat);


        PredictionWeather predictionWeather;
        try {
            this.open();
            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery(query.toString());
            ArrayList<PredictionWeather> list = new ArrayList<>();

            while (resultSet.next()) {
                predictionWeather = new PredictionWeather();
                predictionWeather.setCity(resultSet.getString("city"));
                Coordinates coordinates = new Coordinates(resultSet.getDouble("longitude"), resultSet.getDouble("latitude"));
                predictionWeather.setCoordinates(coordinates);
                predictionWeather.setTemperature(resultSet.getDouble("temperature"));

                predictionWeather.setHumidty(resultSet.getDouble("humidity"));
                predictionWeather.setPressure(resultSet.getDouble("pressure"));

                StringBuilder s = new StringBuilder(resultSet.getString("date"));
                Timestamp ts = Timestamp.valueOf(s.toString());
                predictionWeather.setPredictionDate(ts);

                list.add(predictionWeather);

            }
            this.close();

            return list;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }


    }

    public void addPredictionWeatherToTheDataBase(PredictionWeather predictionWeather) {

        try {
            this.open();
            this.stmt = c.createStatement();
            System.out.println(predictionWeather.getCity());
            StringBuilder s = new StringBuilder();
            s.append("INSERT INTO PredictionWeather VALUES(");
            s.append(predictionWeather.getCoordinates().getLon() + ",");
            s.append(predictionWeather.getCoordinates().getLat() + ", '");
            s.append(predictionWeather.getPredictionDate() + "' , '");
            s.append(predictionWeather.getCity() + "',");
            s.append(predictionWeather.getTemperature() + ",");
            s.append(predictionWeather.getHumidty() + ",");
            s.append(predictionWeather.getPressure() + ")");

            stmt.execute(s.toString());
            this.close();



        } catch (Exception e) {

        }
    }


    //CONSULTATIONS TO ADMIN FAVORITECITY


    public List<String> listFavoriteCities() throws ThereAreNoFavouriteCities {

        List<String> favouriteCities = new ArrayList<>();

        try {
            this.open();

            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM FavoriteCity");

            while (resultSet.next()) {
                String city = resultSet.getString("city");
                favouriteCities.add(city);
                System.out.println(city);
            }

            this.close();

        } catch (Exception e) {

            return null;
        }

        if (favouriteCities.size() == 0 || favouriteCities == null) {
            //throw new ThereAreNoFavouriteCities();
        }
        return favouriteCities;


    }

    public boolean addCityToFavorite(String city) {
        try {

            this.open();

            this.stmt = c.createStatement();
            stmt.execute("INSERT INTO FAVORITECITY(city) VALUES('" + city + "')");

            this.close();
            return true;

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("No se ha podido añadir la ciudad a favoritos (ya está en la BBDD)");
        }
        return false;
    }

    public boolean removeCityFromFavorite(String city) {
        //todo hay que comprobar si al intentar borrar algo que no existe da error, en teoría no deberia
        //por tanto, se debe ver si la ciudad estaba en la bbdd o no

        try {
            if (this.existsCity(city)) {
                this.open();

                this.stmt = c.createStatement();
                stmt.execute("DELETE FROM FAVORITECITY WHERE city = '" + city + "'");

                this.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean existsCity(String city) {

        String existingCity = null;

        try {
            this.open();
            this.stmt = c.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM FAVORITECITY WHERE city = '" + city + "'");

            //while (resultSet.next()){
            existingCity = resultSet.getString("city");
            //}

            this.close();

        } catch (Exception e) {
            //no esta la ciudad
        }

        return existingCity != null;
    }

    //CONSULTATIONS TO ADMIN FAVORITECOORDINATES


    public List<Coordinates> listFavoriteCoordinates() {

        List<Coordinates> favouriteCoordinates = new ArrayList<>();

        try {
            this.open();

            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM FavoriteCoordinates");

            while (resultSet.next()) {
                double longitude = resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");

                Coordinates coordinate = new Coordinates(longitude, latitude);
                favouriteCoordinates.add(coordinate);
            }

            this.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return favouriteCoordinates;
    }

    public boolean addCoordinatesToFavorite(Coordinates coordinates) {
        try {
            this.open();
            this.stmt = c.createStatement();
            StringBuilder s = new StringBuilder();
            s.append("INSERT INTO FAVORITECOORDINATES(longitude,latitude) VALUES(");
            s.append(coordinates.getLon() + ",");
            s.append(coordinates.getLat() + ")");

            stmt.execute(s.toString());

            this.close();
            return true;

        } catch (Exception e) {
            System.out.println("Error al intentar añadir una coordenada a favoritos en la BBDD");
            //e.printStackTrace();
            return false;
        }
    }

    public boolean removeCoordinatesFromFavorite(Coordinates coordinates) {

        //todo cambiar la comprobación de si existe la coordenada en la BBDD del controlador, hacerla aquí

        try {

            this.open();

            this.stmt = c.createStatement();
            StringBuilder s = new StringBuilder();
            s.append("DELETE FROM FAVORITECOORDINATES WHERE longitude =");
            s.append(coordinates.getLon() + " AND latitude =");
            s.append(coordinates.getLat());
            stmt.execute(s.toString());

            this.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al intentar borrar una coordenada de favoritos en la BBDD");
            //e.printStackTrace();
            return false;
        }
    }


    //LABELS

    public boolean addLabel(String label, Coordinates coordinates) {

        try {
            this.open();

            this.stmt = c.createStatement();
            StringBuilder s = new StringBuilder();
            s.append("INSERT INTO LABELS(label,longitude,latitude) VALUES(");
            s.append("'" + label + "',");
            s.append(coordinates.getLon() + ",");
            s.append(coordinates.getLat() + ")");

            stmt.execute(s.toString());
            this.close();

            return true;

        } catch (Exception e) {
            //La etiqueta ya está
        }
        return false;
    }

    public boolean removeLabel(String label) {

        try {
            if (this.existsLabel(label)) {
                this.open();

                this.stmt = c.createStatement();
                StringBuilder s = new StringBuilder();
                s.append("DELETE FROM LABELS WHERE label = '" + label + "'");
                stmt.execute(s.toString());

                this.close();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //TODO: Revisar creado por @Zayda
    public Map<String, Coordinates> getLabels() {

        TreeMap<String, Coordinates> labels = new TreeMap<>();

        try {
            this.open();

            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Labels");

            while (resultSet.next()) {
                double longitude = resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");
                String label = resultSet.getString("label");    //TODO: Revisar

                Coordinates coordinate = new Coordinates(longitude, latitude);
                labels.put(label, coordinate);
            }

            this.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return labels;

    }

    public Coordinates getCoordinatesOfLabel(String label) {

        Coordinates coordinates = null;

        try {
            this.open();

            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM LABELS WHERE label = '" + label + "'");

            resultSet.getString("label");
            double longitude = resultSet.getDouble("longitude");
            double latitude = resultSet.getDouble("latitude");
            coordinates = new Coordinates(longitude, latitude);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return coordinates;
    }

    private boolean existsLabel(String label) {

        String existingLabel = null;

        try {
            this.open();
            this.stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM LABELS WHERE label = '" + label + "'");

            existingLabel = resultSet.getString("label");

            this.close();
        } catch (Exception e) {
            //No esta la label
        }

        return existingLabel != null;
    }

    /*public Map<String, Coordinates> getLabels(){

        try{



        }catch (Exception e){
            e.printStackTrace();
        }
    }
*/


}
