package data;

import data.model.*;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
/**
 * Created by shakeeb on 3/31/16.
 */

/**
 * OfyHelper, a ServletContextListener, is setup in web.xml to run before a JSP is run.  This is
 * required to let JSP's access Ofy.
 * This will be invoked as part of a warmup request, or the first user request if no warmup request.
 */
public class OfyHelper implements ServletContextListener{
    public void contextInitialized(ServletContextEvent event){
        // because we're using spring idk how exactly this will be called
        // its meant to be called before a servlet is called so that the datastore can be loaded up
        //register all the models via objectify
        ObjectifyService.register(Chapter.class);
        ObjectifyService.register(Comment.class);
        ObjectifyService.register(Page.class);
        ObjectifyService.register(Rating.class);
        ObjectifyService.register(Series.class);
        ObjectifyService.register(UserData.class);

    }

    public void contextDestroyed(ServletContextEvent event){
        // app engine does not currently invoke this method
    }

}
