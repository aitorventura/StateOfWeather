package es.uji.ei1048.weatherApp;

public class Label {
    private double lon;
    private double lat;
    private String label;

    public Label(double lon, double lat, String label) {
        this.lon = lon;
        this.lat = lat;
        this.label = label;
    }


    public String getLabel(double lon, double lat) {
        return label;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getLabel() {
        return label;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
