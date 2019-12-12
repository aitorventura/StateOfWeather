package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.Label;
import es.uji.ei1048.weatherApp.SQLiteDB;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class SavedLabels {

    private SQLiteDB sqLiteDB;

    public SavedLabels() {
        this.sqLiteDB = new SQLiteDB();
    }

    public Map<String, Coordinates> getAllLabels() {
        throw new NotImplementedException();
    }

    public boolean deleteLabel(String label) {
        return sqLiteDB.removeLabel(label);
    }

    public boolean addLabel(String label, Coordinates coordinates) {

        if (coordinates.areValid()){
            return sqLiteDB.addLabel(label, coordinates);
        }

        return false;
    }

    public Coordinates getCoordinatesOfLabel(String label) {
        return sqLiteDB.getCoordinatesOfLabel(label);
    }


}
