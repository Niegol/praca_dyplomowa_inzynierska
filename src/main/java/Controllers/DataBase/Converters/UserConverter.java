package Controllers.DataBase.Converters;

import Controllers.DataBase.FXModels.UserFX;
import Controllers.DataBase.models.User;

public class UserConverter {
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

