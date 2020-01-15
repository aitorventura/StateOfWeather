package es.uji.ei1048.weatherApp.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Coordinates {
    private double lon;
    private double lat;

    public Coordinates(double lon, double lat) {
        double longi = new BigDecimal(lon).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double lati = new BigDecimal(lat).setScale(2, RoundingMode.HALF_UP).doubleValue();

        this.lon = longi;
        this.lat = lati;
    }



    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }


    public void setLat(double lat) {
        double lati = new BigDecimal(lat).setScale(2, RoundingMode.HALF_UP).doubleValue();

        this.lat = lati;
    }

    public void setLon(double lon) {
        double longi = new BigDecimal(lon).setScale(2, RoundingMode.HALF_UP).doubleValue();

        this.lon = longi;
    }

    public boolean areValid(){

        if(lon < 180 && lon > -180) {
            if (lat < 90 && lat > -90) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString(){
        return lon+", "+lat;
    }


   @Override
    public boolean equals(Object obj) {

        try{
            Coordinates coordObj = (Coordinates) obj;
            if(coordObj.getLon() == this.lon && coordObj.getLat() == this.lat){
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }

    }

}
