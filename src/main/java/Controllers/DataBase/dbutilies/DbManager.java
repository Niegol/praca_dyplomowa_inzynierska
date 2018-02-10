package Controllers.DataBase.dbutilies;

import Classes.dialogs.DialogsUtils;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Klasa odpowiedzialna za zarządzanie połączeniem z bazą danych.
 */
public class DbManager {
//    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver"; //driver do bazy danych
    /**
     * Adres URL bazy danych.
     */
    private final static String JDBC_URL = "jdbc:mysql://localhost:3306/BookingBase";
    /**
     * Login do bazy danych.
     */
    private final static String JDBC_USER = "root";
    /**
     * Hasło do bazy danych
     */
    private final static String JDBC_PASSWORD = "admin";

    /**
     * Obecny status połączenia z bazą danych
     */
    private static ConnectionSource connectionSource;

    /**
     * Metoda inicjalizujaca polaczenie. W przypadku braku możliwości jego dokonania zostanie wyświetlony odpowiedni
     * komunikat.
     */
    private static void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    /**
     * Metoda zwraca aktualny status połączenia, a w przypadku jego braku (NULL) tworzy nowe połączenie.
     * @return aktualny status połączenia
     */
    public static ConnectionSource getConnectionSource(){
        if (connectionSource == null){
            createConnectionSource();
        }
        return connectionSource;
    }

    /**
     * Metoda przerywająca połączenie. Dokonuje się to tylko w przypadku, gdy połączenie istnieje (nie jest NULL)
     */
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
