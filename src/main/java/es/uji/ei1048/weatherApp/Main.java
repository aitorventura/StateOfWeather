package es.uji.ei1048.weatherApp;

public class Main {
    public static void main(String[] args){
        SQLiteDB sqLiteDB = new SQLiteDB();

        System.out.println("SOLO DEBERÍA ESTAR CASTELLÓN EN FAVORITOS");
        sqLiteDB.listFavoriteCities();

        System.out.println();
        System.out.println("CASTELLÓN Y VALENCIA EN FAV");
        sqLiteDB.addCityToFavorite("Valencia");
        sqLiteDB.listFavoriteCities();

        System.out.println();
        System.out.println("SOLO DEBERÍA ESTAR CASTELLÓN EN FAVORITOS");
        sqLiteDB.removeCityFromFavorite("Valencia");
        sqLiteDB.listFavoriteCities();

        sqLiteDB.closeConnection();

    }
}
