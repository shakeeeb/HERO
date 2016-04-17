package data.repository;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.Key;
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
        System.out.println(Id);
        String[] cparts = Id.split("\\^");
        String[] sparts = Id.split("~");
        String chapterId = cparts[0];
        String seriesId = sparts[0];
        System.out.println("chapter- <" + chapterId + ">");
        System.out.println("series- <" + seriesId + ">");
        Series s = ofy().load().type(Series.class).id(seriesId).now();
        Key<Chapter> key = Key.create(Key.create(s), Chapter.class, chapterId);
        Chapter c = ofy().load().key(key).now();
        Key<Page> pkey = Key.create(Key.create(c), Page.class, Id);
        return ofy().load().key(pkey).now();
    }

    //getByKey()
    public Page getByKey(Chapter chap, String Id){
        String cid = chap.getChapterId();
        if(!Id.contains(cid)){
            System.out.println("no the right series for chapter");
            return null;
        }
        Key<Page> key = Key.create(Key.create(chap), Page.class, Id);
        return ofy().load().key(key).now();
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

    // creating a new page
    public Page create(Series theSeries, Chapter theChapter, ArrayList<Page> priors){

        // get chapters last max
        int n = theChapter.getMax();
            // increment
            // set the new page number
        Page p = null;
        if(priors != null){
            p = new Page(theSeries, theChapter, n, priors);
        } else {
            p = new Page(theSeries, theChapter, n);
        }
        // p = new Page(theSeries, theChapter, n, priors);

        // create new page isomg series and chapter info


        // go through list of prior pages
            // add link to new page for each prior
        if(priors != null){
            for(Page p2 : priors){
                p2.setNext(p);
            }
        }
        // save everything
            // save each prior
        if(priors != null){
            for(Page p2 : priors){
                ofy().save().entity(p2).now();
            }
        }
            // save current page
        ofy().save().entity(p).now();
            // save chapter
        ofy().save().entity(theChapter).now();
        // return new page
        return p;
    }

    //overload create with a whole bunch of stuff

    //update(Id, stuff to update...)
    public void update(Page p){
        ofy().save().entity(p);
    }

    public void save(Page p){
        ofy().save().entity(p);
    }

    public void saveMulitple(ArrayList<Page> pages){
        for(Page p: pages){
            save(p);
        }
    }

    //delete(Id)
    public void delete(Chapter c, Page p){
        // for any page which links to this page
        // make a find child/ haschild
        // its a big deal to edit up the chapter
        c.deletePage(p); // removes any links to the page that is to be deleted
        ofy().delete().entity(p);
        // delete all links to the object
    }
}
