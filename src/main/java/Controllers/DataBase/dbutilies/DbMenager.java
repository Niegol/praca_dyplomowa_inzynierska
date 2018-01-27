package Controllers.DataBase.dbutilies;

import Classes.dialogs.DialogsUtils;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.io.IOException;
import java.sql.SQLException;

/*
Klasa sluzaca do nawiazywania i konczenia polaczenia z baza danych
 */

public class DbMenager {
    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //driver do bazy danych
    private final static String JDBC_URL = "jdbc:mysql://localhost:3306/BookingBase"; //adres URL bazy
    private final static String JDBC_USER = "root";         //dane do logowania sie do bazy
    private final static String JDBC_PASSWORD = "niegol";   //

    private static ConnectionSource connectionSource;

    // metoda inicjalizujaca polaczenie
    private static void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    // metoda zwraca informacje o polaczeniu
    public static ConnectionSource getConnectionSource(){
        if (connectionSource == null){
            createConnectionSource();
        }
        return connectionSource;
    }

    // metoda zamykajaca polaczenie z baza
    public static void closeConnectionSource(){
        if(connectionSource != null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                DialogsUtils.errorDialog(e.getMessage());
            }
        }
    }

}
