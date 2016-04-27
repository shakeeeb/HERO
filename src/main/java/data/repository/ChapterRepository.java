package data.repository;

import com.googlecode.objectify.Key;
import data.model.*;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shakeeb on 3/31/16.
 */
public class ChapterRepository {
    //getAll()
    public List<Chapter> getAll() {
        List<Chapter> chapters = ofy().load().type(Chapter.class).list();

        return chapters;
    }

    //getById(Id)
    public Chapter getById(String Id){
        String seriesID = Id.split("~")[0];
        Series s = ofy().load().type(Series.class).id(seriesID).now();
        Key<Chapter> key = Key.create(Key.create(s), Chapter.class, Id);
        return ofy().load().key(key).now();
    }

    public Chapter getByKey(Series theSeries, String Id){
        String sid = theSeries.getName();
        if(!Id.contains(sid)){
            System.out.println("wrong Series for chapterId");
            return null;
        }
        Key<Chapter> key = Key.create(Key.create(theSeries), Chapter.class, Id);
        return ofy().load().key(key).now();
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
    public Chapter create(String theName, UserData theAuthor, Series theSeries, int chapterNo){
        //public Chapter(String theName, UserData theAuthor, Series theSeries, int chapterNo, Page theRoot)
        //Page p = new Page();

        //check i series exists
        if(ofy().load().entity(theSeries).now() != null){
            Chapter c = new Chapter(theName, theAuthor, theSeries, chapterNo);
            // make chapter
            Page p = new Page(theSeries, c, c.getMax());
            // make page
            theSeries.addChapter(c);
            // add chapter to series
            c.setRoot(p);
            ofy().save().entity(theSeries).now();
            ofy().save().entity(p).now();
            ofy().save().entity(c).now();
            return c;
        } else {
            return null;
        }
        // if not return null
        // the root should know the chapter it's involved in
    }

    //overload create with a whole bunch of stuff

    //update(Id, stuff to update...)
    public void update(Chapter c){
        // save each page in the chapter
        ArrayList<Page> pages = c.getAllPages();
        for(Page p: pages){
            System.out.println("Chapter Repo -saving page:" + p.getPageId());
            ofy().save().entity(p).now();
        }
        ofy().save().entity(c).now();
    }

    //delete(Id)
    public void delete(Chapter c){
        // gotta manage relations to the series
        // gotta manage relations to the pages too
        ofy().delete().entity(c).now();
    }
}
