package es.uji.ei1048.weatherApp.e2e;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.PredictionWeather;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class E2EPredictionWeatherCoordinates extends E2ETestBed {

    @Test
    public void validPredictionWeatherConsultationUsingCoordinates(){
        //Given: coordenadas válidas
        double lon =  39.9945711;
        double lat = -0.071089;
        Coordinates coordinates = new Coordinates(lon, lat);

        //When : consulto el tiempo de los proximos 3 dias para esas coordenadas
        List<PredictionWeather> listPredictionWeather = weatherAppFacade.previsionOfWeatherCoordinates(coordinates);

        //Then
        assertTrue(listPredictionWeather.size() ==  3);

        for(PredictionWeather predictionWeather: listPredictionWeather){
            assertTrue(predictionWeather.getTemperature() > 5);
            assertTrue(predictionWeather.getTemperature() < 25);
        }


    }

    @Test(expected = NotValidCoordinatesException.class)
    public void invalidPredictionWeatherConsultationUsingCoordinates(){
        //Given: coordenadas no válidas
        double lon =   -500.908;
        double lat = 56.872;
        Coordinates coordinates = new Coordinates(lon, lat);


        //When : consulto el tiempo de los proximos 3 dias para esas coordenadas
        List<PredictionWeather> listPredictionWeather = weatherAppFacade.previsionOfWeatherCoordinates(coordinates);

        //Then: espero que se lance una excepcion

    }

}
