package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.exceptions.NoConnectionException;
import org.junit.Test;

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



    @Test(expected = NoConnectionException.class)
    public void mapWithCurrentWeatherAndOffline(){

        //Given: coordenadas y datos que quiero ver
        double lon =  39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);
        String tipoDatos = "temperature";


        //When: hay conexión
        //TODO: No hay conexión


        //Then: se lanza yba excepción diciendo que no hay conexión

    }




}
