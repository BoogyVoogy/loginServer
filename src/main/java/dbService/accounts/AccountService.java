package dbService.accounts;

import dbService.DBException;
import dbService.DBService;
import dbService.dataSet.UsersDataSet;
import org.hibernate.HibernateException;
import sun.plugin.util.UserProfile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bugarchev A.A. on 2016.
 */
public class AccountService {
    public AccountService(){

    }

    public void addNewUser(String login, String password){
        DBService dbService = new DBService();
        try {
            long userId = dbService.addUser(login,password);
            System.out.println("Added user id: " + userId);


        } catch (DBException e) {
            e.printStackTrace();
        }
    }
    public UsersDataSet getUserByLogin(String login) throws DBException{
        DBService dbService = new DBService();
        try {
            UsersDataSet dataSet = dbService.getUser(login);
            System.out.println("User data set: " + dataSet);
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }
}
