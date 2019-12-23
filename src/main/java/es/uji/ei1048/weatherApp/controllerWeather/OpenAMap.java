package es.uji.ei1048.weatherApp.controllerWeather;

import java.io.IOException;
import java.net.URISyntaxException;

public class OpenAMap {

    public void openAMapWithThisPhenomenon(String phenomenon) {
        double lon = -4.1968;
        double lat = 40.220478;

        //Zoom predeterminado para la página
        int zoom = 6;


        //Mostrar ciudades o no
        boolean ciudades = true;

        String url = "https://openweathermap.org/weathermap?basemap=map&cities=" + ciudades + "&layer=" + phenomenon + "&lat=" + lat + "&lon=" + lon + "&zoom=" + zoom;


        OpenAMap.goToURL(url);

    }

    public static void goToURL(String URL) {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI(URL);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
