package data.repository;

import static com.googlecode.objectify.ObjectifyService.ofy;
import data.model.*;

import java.util.ArrayList;

/**
 * Created by shakeeb on 3/31/16.
 */
public class PageRepository {
    //getAll()
    public ArrayList<Page> getAll(){
        // get every single page
        return (ArrayList<Page>)ofy().load().type(Page.class).list();
    }

    //getById(Id)
    public Page getById(String Id){
        return ofy().load().type(Page.class).id(Id).now();
    }

    //getByOtherThingsIfNeedBe

    //exists(Id)

    public boolean exists(String Id){
        Page p = ofy().load().type(Page.class).id(Id).now();
        if(p != null){
            return true;
        } else {
            return false;
        }
    }

    //create(Id, stuff)

    public void create(Series theSeries, Chapter theChapter, int PageNumber){
        Page p = new Page(theSeries, theChapter, PageNumber);
    }

    //overload create with a whole bunch of stuff

    //update(Id, stuff to update...)
    public void update(Page p){
        ofy().save().entity(p);
    }

    //delete(Id)
    public void delete(Page p){
        ofy().delete().entity(p);
        // delete all links to the object
    }
}
