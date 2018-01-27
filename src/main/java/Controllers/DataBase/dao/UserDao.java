package Controllers.DataBase.dao;

import Classes.dialogs.DialogsUtils;
import Controllers.DataBase.models.User;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends CommonDao{
    public UserDao(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    //metoda korzystajaca z raw query wykonwyana przy logowaniu do aplikacji. Przechodzi do glownego okna menu
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

    public boolean isLoggedAdmin(List<User> loggedUser){
        boolean isAdmin = false;
        User user = loggedUser.get(0);
        isAdmin = user.isAccess();
        return isAdmin;
    }

    public String nick(List<User> list){
        String nick = list.get(0).getNick();
        return nick;
    }

    public void update(User user){
        createOrUpdate(user);
    }
}
