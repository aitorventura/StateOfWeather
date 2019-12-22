package es.uji.ei1048.weatherApp.controllerWeather;

import es.uji.ei1048.weatherApp.Coordinates;
import es.uji.ei1048.weatherApp.Label;
import es.uji.ei1048.weatherApp.SQLiteDB;
import es.uji.ei1048.weatherApp.exceptions.NotValidCoordinatesException;
import es.uji.ei1048.weatherApp.interfaces.IStore;
import es.uji.ei1048.weatherApp.interfaces.IWeatherService;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Map;

public class SavedLabels {

    private IStore sqLiteDB;

    public SavedLabels() {
        this.sqLiteDB = new SQLiteDB();
    }

    public SavedLabels(IStore sqLiteDB) {
        this.sqLiteDB = sqLiteDB;
    }

    public SavedLabels(IStore iStore, IWeatherService iWeatherService){
        this.sqLiteDB = iStore;
    }

    //TODO: Revisar, cambiado por @Zayda
    public Map<String, Coordinates> getAllLabels() {
        return sqLiteDB.getLabels();
        //throw new NotImplementedException();
    }

    public boolean deleteLabel(String label) {
        return sqLiteDB.removeLabel(label);
    }

    public boolean addLabel(String label, Coordinates coordinates) {

        if (coordinates.areValid()){
            return sqLiteDB.addLabel(label, coordinates);
        }
        throw new NotValidCoordinatesException();
        //return false;
    }

    public Coordinates getCoordinatesOfLabel(String label) {
        return sqLiteDB.getCoordinatesOfLabel(label);
    }


}
