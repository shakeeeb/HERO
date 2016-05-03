package data.repository;

import data.model.*;

import java.util.ArrayList;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by shakeeb on 3/31/16.
 */
public class  UserRepository {
    //getAll()
    public ArrayList<UserData> getAllUsers(){
        // query database for all users
        ArrayList returner = (ArrayList)ofy().load().type(UserData.class).list();
        // just gets all of the users
        return returner;
    }


    //getById(Id)
    public UserData getUserById(String Id){
        return  ofy().load().type(UserData.class).id(Id).now();
    }

    //getByOtherThingsIfNeedBe
    // This is where strategically selecting indexes is a big deal

    //exists(Id)
    public boolean exists(String Id){
        UserData u = ofy().load().type(UserData.class).id(Id).now();
        if(u != null){
            return true;
        } else {
            return false;
        }
    }

    //create(Id, stuff)
    public UserData create(String email){
        // creates a new user and places it into the datastore
        UserData u = new UserData(email);
        ofy().save().entity(u).now();
        return u;
    }

    //update(Id, stuff to update...)
    // this will be super overloaded

    /**
     * This one will rely on something else to have queried for user u previously
     * and then will simply store user u back into the datastore
     * ie, the data logic was handled outside of here
     * @param u
     */
    public void update(UserData u){
        ofy().save().entity(u).now();
    }

    /**
     * methods like these specific update methods make sure that data logic
     * is only handled down here, at the repository level
     * @param Id
     * @param nickname
     */
    public void updateUserNickname(String Id, String nickname){
        UserData u = ofy().load().type(UserData.class).id(Id).now();
        u.setNickname(nickname);
        ofy().save().entity(u).now();
    }

    public void updateUserAboutMe(String Id, String about){
        UserData u = ofy().load().type(UserData.class).id(Id).now();
        u.setNickname(about);
        ofy().save().entity(u).now();
    }

    //delete(Id)
    public void delete(String Id){
        ofy().delete().type(UserData.class).id(Id).now();
    }
}
