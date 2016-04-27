package controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import data.DbContext;
import data.model.Chapter;
import data.model.Page;
import data.model.Series;
import data.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class ChapterIndexController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="chapter-index", method = RequestMethod.GET)
    public String chapterIndexPage(ModelMap model) {
        System.out.println("Going to chapter-index ");
        return "chapter-index";
    }

    @RequestMapping(value = "/chapter-index/get/{series-ID}", method = RequestMethod.GET)
    public @ResponseBody
    JsonObject getPageOptions(@PathVariable(value = "series-ID") String seriesID, ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();
        boolean isSubscribed = false;
        boolean userLoggedIn;
        ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
        Series s = db.seriesRepo.getById(seriesID);
        chapterList = s.getChapters();
        json.add("chapterList", gson.toJsonTree(chapterList));
        json.add("series", gson.toJsonTree(s));

        UserService userService = UserServiceFactory.getUserService();
        userLoggedIn = userService.isUserLoggedIn();

        if(userLoggedIn == false) {
            return json;//"Error: User not logged in";
        }
        //If the user is not logged in, don't display the subscribe button.

        // load user from the datastore
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());
        if(user == null) {
            return json;//"Error: Unable to load user from datastore";
        }
        if (user.isSubscribed(s)) {
            isSubscribed = true;
        }

        json.add("isSubscribed", gson.toJsonTree(isSubscribed));
        json.add("isUserLoggedIn", gson.toJsonTree(userLoggedIn));
        return json;
    }

    @RequestMapping(value = "/chapter-index/{series-ID}", method = RequestMethod.GET)
    public String loadChapterIndex(@PathVariable(value = "series-ID") String seriesID, ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
        Series s = db.seriesRepo.getById(seriesID);
        chapterList = s.getChapters();
        String chapterID = s.getChapters().get(0).getChapterId();
        String rootID = chapterList.get(0).getRoot().getPageId();
        System.out.println("The root ID is: " + rootID);
        System.out.println("The chapterID is: " + chapterID);
        Gson gson = new GsonBuilder().create();
        json.add("chapterList", gson.toJsonTree(chapterList));
        json.add("series", gson.toJsonTree(s));
        model.addAttribute("rootID", rootID);
        model.addAttribute("seriesID", seriesID);
        model.addAttribute("chapterID", chapterID);
        return "chapter-index";
    }

    @RequestMapping(value = "/chapter-index/subscribe/{series-ID}", method = RequestMethod.GET)
    public @ResponseBody
    JsonObject subscribe(@PathVariable(value = "series-ID") String seriesID, ModelMap model, HttpSession session, HttpServletRequest req) {
        System.out.println("In Subscription Mapping");


        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();
        boolean isSubscribed = false;
        boolean userLoggedIn;
        UserService userService = UserServiceFactory.getUserService();
        userLoggedIn = userService.isUserLoggedIn();

        if(userLoggedIn == false) {
            return json;//"Error: User not logged in";
        }

        // load user from the datastore
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());
        if(user == null) {
            return json;//"Error: Unable to load user from datastore";
        }

        System.out.println("Series ID: " + seriesID);
        Series s = db.seriesRepo.getById(seriesID);

        if (!user.isSubscribed(s))
        {
            user.addSubscription(s);
            System.out.println("adding Subscription to: " + s);
            isSubscribed = true;
            System.out.println("Subscribed: " + isSubscribed);
            db.userRepo.update(user);
        }
        else
        {
            user.removeSubscription(s);
            System.out.println("Removed subscription from: " + s);
            isSubscribed = false;
            System.out.println("Subscribed: " + isSubscribed);
            db.userRepo.update(user);
        }

        json.add("subscriptionToggled", gson.toJsonTree(isSubscribed));
        return json;
    }
}
