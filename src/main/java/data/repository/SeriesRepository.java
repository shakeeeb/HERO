package data.repository;

import com.googlecode.objectify.cmd.Query;
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

    public Query<Series> getAllSeriesAsAQuery(){
       return ofy().load().type(Series.class);
    }

    public Query<Series> getSeriesByAuthor(String name){
        return ofy().load().type(Series.class).filter("authorName", name);
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
    // certain kinds of queries-- refine vs. query
    //refine will be sent a query obejct and refine it

    public Query<Series> grabQueryByMainGenre(String Genre){
        return ofy().load().type(Series.class).filter("mainGenre", Genre);
    }

    public Query<Series> grabQueryByName(String Name){
        Query<Series> q = ofy().load().type(Series.class);
        q = q.filter("nameCopy >=", Name);
        return q.filter("nameCopy <", Name + "\ufffd");
    }

    public Query<Series> refineQueryByMainGenre(Query<Series> toRefine, String Genre){
        return toRefine.filter("mainGenre", Genre);
    }

    public Query<Series> grabQueryByTag(String Genre){
        return ofy().load().type(Series.class).filter("tags", Genre);
    }

    public Query<Series> refineQueryByTag(Query<Series> toRefine, String Genre){
        return toRefine.filter("tags", Genre);
    }

    public Query<Series> refineQueryByAuthorName(Query<Series> toRefine, String authorName){
        return toRefine.filter("authorName", authorName);
    }

    public Query<Series> grabQueryByLatestUpdate(){
        return ofy().load().type(Series.class).order("-updateTime");
    }

    public Query<Series> refineQueryByLatestUpdate(Query<Series> toRefine){
        return toRefine.order("-updateTime");
    }

    public Query<Series> grabQueryByLeastUpdate() {
        return ofy().load().type(Series.class).order("+updateTime");
    }

    public Query<Series> refineQueryByLeastUpdate(Query<Series> toRefine){
        return toRefine.order("+updateTime");
    }

    public Query<Series> grabQueryByStarCount(double starCount){
        return ofy().load().type(Series.class).filter("rating >", starCount);
    }

    public Query<Series> refineByStarCount(Query<Series> toRefine, double starCount){
        return toRefine.filter("rating >", starCount);
    }

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
    public void update(Series s){

        //save all chapters in series too
        ArrayList<Chapter> chapters = s.getChapters();
        for(Chapter c: chapters){
            ofy().save().entity(c).now();
        }
        ofy().save().entity(s).now();
    }

    //delete(Id)
    public void delete(String Id){
        // when you delete a series, you've got to delete everyhting within the series too...
        // as well as delete the links to a series
        // at least, unlink it from the author
        Series s = ofy().load().type(Series.class).id(Id).now();
        UserData a = s.getAuthor();
        a.removeSeries(s);
        ofy().delete().type(Series.class).id(Id).now();
    }

    public void delete(Series s, UserData author){
        author.removeSeries(s);
        ofy().delete().entity(s).now();
        ofy().delete().entity(author).now();
    }
}
