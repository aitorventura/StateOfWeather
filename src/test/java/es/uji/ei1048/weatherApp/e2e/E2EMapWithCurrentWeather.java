package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.Coordinates;
import org.junit.Test;

import java.util.List;

public class E2EMapWithCurrentWeather extends  E2ETestBed{

    @Test
    public void mapWithCurrentWeatherAndConnection(){

        //Given: coordenadas y datos que quiero ver
        double lon =  39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);
        String tipoDatos = "temperature";


        //When: hay conexión
        //TODO: Hay conexión


        //Then: se muestra el mapa mostrando el tipo de datos pedido

    }



    @Test
    public void mapWithCurrentWeatherAndOffline(){

        //Given: coordenadas y datos que quiero ver
        double lon =  39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);
        String tipoDatos = "temperature";


        //When: hay conexión
        //TODO: No hay conexión


        //Then: se muestra el mapa mostrando el tipo de datos pedido

    }




}
