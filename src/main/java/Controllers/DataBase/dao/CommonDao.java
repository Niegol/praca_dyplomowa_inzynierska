package Controllers.DataBase.dao;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.models.BaseModel;
import Controllers.DataBase.models.Customer;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Jest klasą abstrakcyjną zawierających szablony metod, które mogą zostać użyte przez wszystkie obiekty modeli
 * bazodanowych. Dzięki tej klasie możliwe jest tworzenie zapytań do baz danych. Korzysta z biblioteki ORMLite.
 */
public abstract class CommonDao {
    /**
     * Połączenie bazodanowe.
     */
    protected final ConnectionSource connectionSource;

    /**
     * Stworzenie każdego DAO wymusza w konstruktorze otworzenie połączenia do bazy. Do tworzenia takich zapytań
     * stworzona została klasa DbManager.
     * @param cs status połączenia z bazą danych.
     */
    public CommonDao(ConnectionSource cs){
        this.connectionSource = cs;
    }

    /**
     * Zwraca DAO, jeżeli połączenie z bazą jest poprawne. W przeciwnym wypadku zostaje wyświetlony odpowiedni
     * komunikat.
     * @param cls obiekt modelu na jakim ma pracować stworzone DAO.
     * @param <T> typ generyczny modelu rozszerzającego BaseModel.
     * @param <I> informacja jakiego rodzaju jest klucz podstawowy modelu.
     * @return stworzone DAO.
     */
    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls){
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    /**
     * Tworzy lub edytuje dane obiektów BaseModel w bazie danych, w przeciwnym razie wyświetla komunikat informujący
     * o błędnym nawiązaniu połączenia do bazy.
     * @param baseModel obiekt, który ma zostać stworzony, albo zedytowany.
     * @param <T> typ generyczny obiektu z którego kożystać będzie metoda.
     * @param <I> informacja jakiego typu jest klucz podstawowy.
     */
    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }


    /**
     * Metoda odświeżająca informacj elementu BaseModel. W przypadku błędnego połączenia z bazą danych zostanie
     * wyświetlony odpowiedni komunikat.
     * @param baseModel obiekt, który ma zostać odświeżony.
     * @param <T> typ generyczny BaseModel odświeżanego obiektu.
     * @param <I> typ generyczny klucza id odświeżanego obiektu.
     */
    public <T extends BaseModel, I> void refresh(BaseModel baseModel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);

        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    /**
     * Metoda usuwająca konkretny obiekt BaseModel z bazy danych. W przypadku błędnego połączenia wyświetlony zostanie
     * odpowiedni komunikat.
     * @param baseModel obiek, który ma zostać usuniety.
     * @param <T> typ generyczny rozszerzający BaseModel usuwanego obiektu.
     * @param <I> typ generyczny klucza id usuwanego obiektu.
     */
    public <T extends BaseModel, I> void delete(BaseModel baseModel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    /**
     * Metoda usuwająca BaseModel z bazy danych po podaniu jego numeru id. W przypadku błędnego połączenia z bazą danych
     * zostanie wyswietlony odpowiedni komunikat.
     * @param cls typ klasy, która ma zostać usunięta.
     * @param id numer id typu Integer obiektu, który ma zostać usunięty.
     * @param <T> typ generyczny rozszerzający BaseModel usuwanego obiektu
     * @param <I> typ generyczny klucza id obiektu, który ma zostać usunięty
     */
    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id){
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            DialogsUtils.errorDialog("Can't delete");
        }
    }

    /**
     * Metoda zwracająca wszystkie obiekty typu BaseModel. W przypadku błędnego połączenia z baządanych zostane
     * wyświetlony odpowiedni komunikat.
     * @param cls typ klasy obiektów, które zwraca baza danych.
     * @param <T> typ generyczny klas otrzymanych obiektów.
     * @param <I> typ generyczny klucza id zwracanych obiektów.
     * @return dao na którym można budować kwerendy do bazy danych.
     */
    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls){
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    /**
     * Metoda zwracja obiekt DAO, na którym można będzie kreować kwerendy do bazy danych. W przypadku błędnego
     * połączenia z bazą wyświetlony zostanie odpowiedni komunikat.
     * @param cls typ obiektu/ów, które zostaną zwrócone po poprawnym stworzeniu kwerendy.
     * @param <T> typ generyczny zwracanych obiektów.
     * @param <I> typ generyczny klucza id zwracanych obiektów.
     * @return lista obiektów BaseModel otrzymanych z bazy danych po poprawnym wykreowaniu zapytania.
     */
    public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls){
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }

}
