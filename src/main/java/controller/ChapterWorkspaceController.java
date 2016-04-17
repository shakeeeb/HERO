package controller;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import data.DbContext;
import data.model.Chapter;
import data.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class ChapterWorkspaceController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="chapter-workspace", method = RequestMethod.GET)
    public String chapterWorkspacePage(ModelMap model) {
        System.out.println("Going to chapter-workspace ");
        return "chapter-workspace";
    }

    @RequestMapping(value="workspace/chapter/{chapterID}", method = RequestMethod.GET)
    public @ResponseBody String getChapter(@PathVariable(value="chapterID") String chapterID) {

        System.out.println("Looking for: " + chapterID);

        // check if user is logged in
        UserService userService = UserServiceFactory.getUserService();
        if(userService.isUserLoggedIn() == false) {
            return "Error: User not logged in";
        }

        // check if user owns chapter being requested
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());
        if(user == null) {
            return "Error: Unable to load user from datastore";
        }

        // check if the chapter being requested is editable (still in draft mode)

        // load the chapter
//        Chapter chapterToLoad = db.chapterRepo.getById(chapterID);
//        if(chapterToLoad == null) {
//            return "Error: Unable to load chapter from datastore";
//        }
        //System.out.println(chapterToLoad.getName());

        List<Chapter> chapters = db.chapterRepo.getAll();
        if(chapters == null) {
            System.out.println("Unable to load any chapters from the datastore");
        } else {
            System.out.println("Number of chapters in datastore: " + chapters.size());
        }
        //System.out.println(chapters.size());
//        if(chapterToLoad != null) {
//            System.out.println(chapterToLoad.getName());
//        } else {
//            System.out.println("Unable to load Chapter: " + chapterID);
//        }

        // return the chapter as a String/JSON
        return chapterID;
    }
}
