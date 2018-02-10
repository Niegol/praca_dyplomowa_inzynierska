package Controllers.DataBase.dao;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.models.User;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa rozszerzająca klasę CommonDao. Umożliwia tworzenie zapytań związanych stricte z obiektami User.
 */
public class UserDao extends CommonDao{
    /**
     * Konstruktor przyjmujący status połączenia z bazą danych. Każdy stworzony obiekt musi posiadać tą informację.
     * @param connectionSource status połączenia z bazą danych.
     */
    public UserDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    /**
     * Metoda sprawdza, czy podane podczas logowania dane Użytkownika aplikacji się zgadzają z wproadzonymi
     * @param log login Użytkownika.
     * @param pass hasło Użytkownika.
     * @return wartość true/false informująca, czy można umożliwić danemu klientowi dostanie się do gółnego okna zarządzajęcego aplikacjom.
     */
    public List<User> getAcess(String log, String pass){
        List<User> result = new ArrayList<User>();
        try {
            QueryBuilder<User, Integer> queryBuilder = getQueryBuilder(User.class);
            queryBuilder.where().eq("nick", log).and().eq("password", pass);
            PreparedQuery<User> userPreparedQuery = queryBuilder.prepare();
            result = getDao(User.class).query(userPreparedQuery);
            return result;

        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
            return result;
        }

    }

    /**
     * Zwracja listę składającą się z jednego elementu- Użytkownika, który uzyskał dostęp do aplikacji. W przypadku błędnego
     * Stworzenia zapytania zostanie wyświetlone odpowiednie okno komunikacyjne.
     * @return obecnie zalogowany użytkownik.
     */
    public List<User> whoIsLoggedIn(){
        List<User> result = new ArrayList<>();
        try {
            QueryBuilder<User, Integer> queryBuilder = getQueryBuilder(User.class);
            queryBuilder.where().eq("is_logged_in", true);
            PreparedQuery<User> userPreparedQuery = queryBuilder.prepare();
            result = getDao(User.class).query(userPreparedQuery);
            return result;
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
            return result;
        }
    }

    /**
     * Metoda sprawdzająca, czy dany Użytkownik posiadafunkcję Admin.
     * @param loggedUser lista składająca się z jednego elementu- Użytkownika zalogowanego.
     * @return wartość true/false czy Użytkownik jest admienem.
     */
    public boolean isLoggedAdmin(List<User> loggedUser){
        boolean isAdmin = false;
        User user = loggedUser.get(0);
        isAdmin = user.isAccess();
        return isAdmin;
    }

    /**
     * Metoda zwracająca nick zalogowanego użytkownika.
     * @param list lista zalogowanych na danym komputerze użytkowników (składa się z jednego elementu).
     * @return nick zalogowanego obecnie użytkownika
     */
    public String nick(List<User> list){
        String nick = list.get(0).getNick();
        return nick;
    }

    /**
     * Metoda aktualicująca dane użytkownika.
     * @param user wprowadzony obiekt modelu User, który ma zostać edytowany.
     */
    public void update(User user){
        createOrUpdate(user);
    }
}
