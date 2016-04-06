package data.repository;

import data.model.*;
import static com.googlecode.objectify.ObjectifyService.ofy;;

import java.util.ArrayList;

/**
 * Created by shakeeb on 3/31/16.
 */
public class ChapterRepository {
    //getAll()
    public ArrayList<Chapter> getAll(){
        ArrayList<Chapter> returner = (ArrayList)ofy().load().type(Chapter.class).list();
        return returner;
    }

    //getById(Id)
    public Chapter getById(String Id){
        return ofy().load().type(Chapter.class).id(Id).now();
    }

    //getByOtherThingsIfNeedBe

    //exists(Id)
    public boolean exists(String Id){
        Chapter c = ofy().load().type(Chapter.class).id(Id).now();
        if(c == null){
            return false;
        } else {
            return true;
        }
    }

    //create(Id, stuff)
    public void create(String theName, UserData theAuthor, Series theSeries, int chapterNo){
        //public Chapter(String theName, UserData theAuthor, Series theSeries, int chapterNo, Page theRoot)
        Page p = new Page();
        Chapter c = new Chapter(theName, theAuthor, theSeries, chapterNo, p);
        p.generateId();
        //c.setRoot(p);
        ofy().save().entity(c).now();
        // update the series so it knows its got a new chapter, and increment the number of chapters
        theSeries.addChapter(c);

        // the root should know the chapter it's involved in
    }

    //overload create with a whole bunch of stuff

    //update(Id, stuff to update...)
    public void update(Chapter c){
        ofy().save().entity(c).now();
    }

    //delete(Id)
    public void delete(Chapter c){
        ofy().delete().entity(c).now();
    }
}
