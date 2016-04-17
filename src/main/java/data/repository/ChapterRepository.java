package data.repository;

import data.model.*;
import static com.googlecode.objectify.ObjectifyService.ofy;;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shakeeb on 3/31/16.
 */
public class ChapterRepository {
    //getAll()
    public List<Chapter> getAll() {
        // ofy().load().type(Chapter.class).list();
        System.out.println("Chapters: " + ofy().load().type(Chapter.class).toString());
        List<Chapter> chapters = ofy().load().type(Chapter.class).list();

        return chapters;
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
    public Chapter create(String theName, UserData theAuthor, Series theSeries, int chapterNo){
        //public Chapter(String theName, UserData theAuthor, Series theSeries, int chapterNo, Page theRoot)
        //Page p = new Page();
        Chapter c = new Chapter(theName, theAuthor, theSeries, chapterNo);
        ofy().save().entity(c).now();
        Page p = new Page(theSeries, c, 0);
        //p.generateId();
        c.setRoot(p);

        // update the series so it knows its got a new chapter, and increment the number of chapters
        theSeries.addChapter(c);
        return c;
        // the root should know the chapter it's involved in
    }

    //overload create with a whole bunch of stuff

    //update(Id, stuff to update...)
    public void update(Chapter c){
        // save each page in the chapter
        ArrayList<Page> pages = c.getAllPages();
        for(Page p: pages){
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
