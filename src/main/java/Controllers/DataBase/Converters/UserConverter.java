package Controllers.DataBase.Converters;

import Controllers.DataBase.FXModels.UserFX;
import Controllers.DataBase.models.User;

/**
 * Klasa umożliwiająca obustronne konwertowanie obiektów bazodanowych klasy User na obiekty UserFX będące ich reprezentacją
 * w oknie aplikacji
 */
public class UserConverter {
    /**
     * Konwertuje obiekt widoku na model bazodanowy
     * @param userFX obiekt widoku klasy Userfx
     * @return model bazodanowy klasy User
     */
    public static User convertToUser(UserFX userFX){
        User user = new User();
        user.setIdUser(userFX.getId());
        user.setName(userFX.getName());
        user.setSurname(userFX.getSurname());
        user.setNick(userFX.getNick());
        user.setPhone(userFX.getPhone());
        user.setEmail(userFX.getEmail());
        user.setAdress(userFX.getAdress());
        user.setPassword(userFX.getPassword());
        user.setAccess(userFX.isAccess());
        user.setLoggedIn(userFX.isIsLogged());
        return user;
    }

    /**
     * Konwertuje model bazodanowy na obiekt widoku
     * @param user model bazodanowy klasy User
     * @return obiek widoku klasy UserFX
     */
    public static UserFX convertToUserFX(User user){
        UserFX userFX = new UserFX();
        userFX.setId(user.getIdUser());
        userFX.setName(user.getName());
        userFX.setSurname(user.getSurname());
        userFX.setNick(user.getNick());
        userFX.setPhone(user.getPhone());
        userFX.setEmail(user.getEmail());
        userFX.setAdress(user.getAdress());
        userFX.setPassword(user.getPassword());
        userFX.setAccess(user.isAccess());
        userFX.setIsLogged(user.getLoggedIn());
        return userFX;
    }
}

