package es.uji.ei1048.weatherApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
