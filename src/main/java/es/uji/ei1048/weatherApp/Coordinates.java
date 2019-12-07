package es.uji.ei1048.weatherApp;

public class Coordinates {
    private double lon;
    private double lat;

    public Coordinates(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }



    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }


    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
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
