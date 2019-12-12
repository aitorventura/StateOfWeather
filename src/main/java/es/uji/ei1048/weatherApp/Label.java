package es.uji.ei1048.weatherApp;

public class Label extends Coordinates {

    String label;

    public Label(double lon, double lat, String label) {
        super(lon, lat);
        this.label = label;

    }

    //TODO usamos esta clase o pasando?

    public String getLabel(){
        return label;
    }
}
