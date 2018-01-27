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

public abstract class CommonDao {
    protected final ConnectionSource connectionSource;

    public CommonDao(ConnectionSource connectionSource){
        this.connectionSource = connectionSource;
    }

    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls){
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        return null;
    }



    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public <T extends BaseModel, I> I findByID(BaseModel baseModel, Integer id){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.queryForId((I) id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends BaseModel, I> void update(BaseModel baseModel, Integer id){
        try {
            Dao<T, Integer> dao = getDao((Class<T>) baseModel.getClass());
            dao.updateId((T)baseModel, id);
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);

        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public <T extends  BaseModel, I> void queryForAll(BaseModel baseModel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public <T extends BaseModel, I> void delete(BaseModel baseModel){
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id){
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
    }

    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls){
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls){
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }

}
