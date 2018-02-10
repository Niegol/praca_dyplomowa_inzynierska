package Controllers.DataBase.Service;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.Converters.UserConverter;
import Controllers.DataBase.FXModels.UserFX;
import Controllers.DataBase.dao.UserDao;
import Controllers.DataBase.dbutilies.DbManager;
import Controllers.DataBase.models.User;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

/**
 * Klasa kontrolująca wywoływanie obiektów klasy UserDao. Tworzy je w przypadkach, gdy spełnione są odpowiednie
 * warunki. Posiada seterry i gettery argumetów tej klasy, dzięki którym możliwe jest operowanie na tychże argumentach.
 */
public class UserService{
    /**
     * Obiekt obserwowalny przechowujący aktualne dane użytkownika.
     */
    private ObjectProperty<UserFX> userFXObjectProperty = new SimpleObjectProperty<>();
    /**
     * Obiekt obserwowalny przechowuje dane w momencie edycji hasła użytkownika.
     */
    private ObjectProperty<UserFX> userFXNewPassObjectProperty = new SimpleObjectProperty<>();


    /**
     * Funkcja zmieniająca status użytkownika na zalogowany po dokonaniu poprawnego logowania.
     * @param log login użytkownika.
     * @param pass hasło użytkownika.
     * @return true/false w zależności czy możliwe jest zalogowanie czy nie.
     */
    public Boolean logIn(String log, String pass){
        UserDao userDao = new UserDao(DbManager.getConnectionSource());
        List<User> result = userDao.getAcess(log,pass);
        if (!result.isEmpty()){
            User user = result.get(0);
            user.setLoggedIn(true);
            userDao.update(user);
            userDao.refresh(user);
            DbManager.closeConnectionSource();
            return true;
        }else {
            DbManager.closeConnectionSource();
            return false;
        }
    }

    /**
     * Funkcja wylogowująca danego użytkownika. Zmienia jego pole isLoggedIn na false.
     */
    public void logOut(){
        UserDao userDao = new UserDao(DbManager.getConnectionSource());
        List<User> result = userDao.whoIsLoggedIn();
        User user = result.get(0);
        user.setLoggedIn(false);
        userDao.update(user);
        DbManager.closeConnectionSource();
    }

    /**
     * Funkcja zwracająca nick (login) zalogowanego do sytemu użytkownika.
     * @return login zalogowanego użytkownika.
     */
    public String getUserNick(){
        UserDao userDao = new UserDao(DbManager.getConnectionSource());
        List<User> list = userDao.whoIsLoggedIn();
        String nick = userDao.nick(list);
        DbManager.closeConnectionSource();
        return nick;
    }

    /**
     * Funkcja zwracająca obiekt, którym jest zalogowany użytkownik.
     * @return zalogowany użytkownik.
     */
    public User getLoggedUser(){
        UserDao userDao = new UserDao(DbManager.getConnectionSource());
        User user = userDao.whoIsLoggedIn().get(0);
        DbManager.closeConnectionSource();
        return user;
    }

    /**
     * Inicjalizuje wartości atrybutów klasy.
     */
    public void init(){
        this.userFXObjectProperty.setValue(UserConverter.convertToUserFX(this.getLoggedUser()));
        this.userFXNewPassObjectProperty.setValue(UserConverter.convertToUserFX(this.getLoggedUser()));
    }

    /**
     * Funkcja sprawdza czy zalogowany obecnie użytkownik jest administratorem, czy nie.
     * @return true/false w zależności czy użytkownik jest administratorem, czy nie.
     */
    public boolean isAdmin(){
        boolean isAdmin;
        UserDao userDao = new UserDao(DbManager.getConnectionSource());
        isAdmin = userDao.isLoggedAdmin(userDao.whoIsLoggedIn());
        DbManager.closeConnectionSource();
        return isAdmin;
    }

    /**
     * Funkcja aktualizująca dane użytkownika. Aby mogła się wykonać przynajmniej jedna zmienna powinna zostać zmieniona.
     * @param phone wprowadzony numer telefonu.
     * @param email wprowadzony adres email.
     * @param adress wprowadzony adres zamieszkania.
     */
    public void update(String phone, String email, String adress){
        try {
            if (!phone.isEmpty() & !email.isEmpty() & !adress.isEmpty()) {
                Boolean change = false;
                int number = Integer.parseInt(phone);
                UserDao userDao = new UserDao(DbManager.getConnectionSource());
                User user = userDao.whoIsLoggedIn().get(0);
                if (number != user.getPhone() & Service.isPhoneCorrect(phone)) {
                    user.setPhone(number);
                    change = true;
                }
                if (!email.equals(user.getEmail()) & Service.isEmailCorrect(email)) {
                    user.setEmail(email);
                    change = true;
                }
                if (!adress.equals(user.getAdress())) {
                    user.setAdress(adress);
                    change = true;
                }

                if (change) {
                    userDao.update(user);
                    DialogsUtils.communicat("Your data has been changed!");
                }else
                    DialogsUtils.communicat("No changes or wrong email format!");

                DbManager.closeConnectionSource();
            }else
                DialogsUtils.communicat("Some field is empty!");

        }catch (NumberFormatException e) {
            DialogsUtils.errorDialog("Wrong number format!");
        }
    }

    /**
     * Funkcja zapisująca zmienione hasło użytkownika do systemu.
     * @param oldPass stare hasło.
     * @param confPass potwierdzenie hasła.
     */
    public void saveUserInDB(String oldPass, String confPass){
        if(oldPass.equals(confPass))
            saveOrUpdate(this.getUserFXNewPassObjectProperty());
        else
            DialogsUtils.communicat("Wrong old password!");
    }

    /**
     * Funkcja zapisująca dane użytkownika z widoku.
     * @param userFX obiekt użytkownika z widoku UserFX, który ma zostać zapisany lub zedytowany w bazie danych.
     */
    public void saveOrUpdate(UserFX userFX){
        UserDao userDao = new UserDao(DbManager.getConnectionSource());
        User user = UserConverter.convertToUser(userFX);
        userDao.createOrUpdate(user);
        DbManager.closeConnectionSource();
    }

    public UserFX getUserFXObjectProperty() {
        return userFXObjectProperty.get();
    }

    public ObjectProperty<UserFX> userFXObjectPropertyProperty() {
        return userFXObjectProperty;
    }

    public void setUserFXObjectProperty(UserFX userFXObjectProperty) {
        this.userFXObjectProperty.set(userFXObjectProperty);
    }

    public UserFX getUserFXNewPassObjectProperty() {
        return userFXNewPassObjectProperty.get();
    }

    public ObjectProperty<UserFX> userFXNewPassObjectPropertyProperty() {
        return userFXNewPassObjectProperty;
    }

    public void setUserFXNewPassObjectProperty(UserFX userFXNewPassObjectProperty) {
        this.userFXNewPassObjectProperty.set(userFXNewPassObjectProperty);
    }
}
