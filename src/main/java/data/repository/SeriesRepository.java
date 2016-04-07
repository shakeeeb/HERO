package data.repository;

import data.model.*;
import java.util.ArrayList;
import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * Created by shakeeb on 3/31/16.
 */
public class SeriesRepository {
    //getAll()
    public ArrayList<Series> getAllSeries(){
        ArrayList<Series> returner = (ArrayList)ofy().load().type(Series.class).list();
        return returner;
    }

    //getById(Id)
    public Series getById(String Id){
        return ofy().load().type(Series.class).id(Id).now();
    }

    //getByOtherThingsIfNeedBe

    public ArrayList<Series> getSeriesByMainGenre(String mainGenre){
        ArrayList<Series> returner = (ArrayList)ofy().load().type(Series.class).filter("mainGenre", mainGenre);
        return returner;
    }

    public ArrayList<Series> getSeriesByTag(String tag){
        ArrayList<Series> returner = (ArrayList)ofy().load().type(Series.class).filter("tags", tag);
        return returner;
    }

    public ArrayList<Series> getSeriesByHighestRated(){
        ArrayList<Series> returner = (ArrayList)ofy().load().type(Series.class).order("-rating").list();
        return returner;
    }

    public ArrayList<Series> getSeriesByUpdateTime(){
        ArrayList<Series> returner = (ArrayList)ofy().load().type(Series.class).order("updateTime").list();
        return returner;
    }

    // TODO add queries

    //exists(Id)
    public boolean exists(String Id){
        Series s = ofy().load().type(Series.class).id(Id).now();
        if(s == null){
            return false;
        } else {
            return true;
        }
    }

    //create(Id, stuff)
    public Series create(String name, String mainGenre, UserData author, String desc){
        // manage the relation, ie add the series to the list of series for the user
        Series s = new Series(name, author, desc, mainGenre);
        author.addSeries(s);
        ofy().save().entity(s);
        ofy().save().entity(author);
        return s;
    }


    //overload create with a whole bunch of stuff

    //update(Id, stuff to update...)
    public void updateSeries(Series s){
        ofy().save().entity(s).now();
    }

    //delete(Id)
    public void deleteSeries(String Id){
        // when you delete a series, you've got to delete everyhting within the series too...
        // as well as delete the links to a series
        // at least, unlink it from the author
        Series s = ofy().load().type(Series.class).id(Id).now();
        UserData a = s.getAuthor();
        a.removeSeries(s);
        ofy().delete().type(Series.class).id(Id).now();
    }
}
