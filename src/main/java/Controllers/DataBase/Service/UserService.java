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

public class UserService{
    private ObjectProperty<UserFX> userFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<UserFX> userFXNewPassObjectProperty = new SimpleObjectProperty<>();


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

    public void logOut(){
        UserDao userDao = new UserDao(DbManager.getConnectionSource());
        List<User> result = userDao.whoIsLoggedIn();
        User user = result.get(0);
        user.setLoggedIn(false);
        userDao.update(user);
        DbManager.closeConnectionSource();
    }

    public String getUserNick(){
        UserDao userDao = new UserDao(DbManager.getConnectionSource());
        List<User> list = userDao.whoIsLoggedIn();
        String nick = userDao.nick(list);
        DbManager.closeConnectionSource();
        return nick;
    }

    public User getLoggedUser(){
        UserDao userDao = new UserDao(DbManager.getConnectionSource());
        User user = userDao.whoIsLoggedIn().get(0);
        DbManager.closeConnectionSource();
        return user;
    }

    public void init(){
        this.userFXObjectProperty.setValue(UserConverter.convertToUserFX(this.getLoggedUser()));
        this.userFXNewPassObjectProperty.setValue(UserConverter.convertToUserFX(this.getLoggedUser()));
    }

    public boolean isAdmin(){
        boolean isAdmin;
        UserDao userDao = new UserDao(DbManager.getConnectionSource());
        isAdmin = userDao.isLoggedAdmin(userDao.whoIsLoggedIn());
        DbManager.closeConnectionSource();
        return isAdmin;
    }

    public void update(String phone, String email, String adress){
        try {
            if (!phone.isEmpty() && !email.isEmpty() && !adress.isEmpty()) {
                Boolean change = false;
                int number = Integer.parseInt(phone);
                UserDao userDao = new UserDao(DbManager.getConnectionSource());
                User user = userDao.whoIsLoggedIn().get(0);
                if (number != user.getPhone()) {
                    user.setPhone(number);
                    change = true;
                }
                if (!email.equals(user.getEmail()) && email.contains("@") && email.contains(".")) {
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

    public void saveUserInDB(String oldPass, String confPass){
        if(oldPass.equals(confPass))
            saveOrUpdate(this.getUserFXNewPassObjectProperty());
        else
            DialogsUtils.communicat("Wrong old password!");
    }

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
