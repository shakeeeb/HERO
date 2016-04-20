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

    @RequestMapping(value = "/chapter-index/{series-ID}", method = RequestMethod.GET)
    public @ResponseBody
    JsonObject getPageOptions(@PathVariable(value = "series-ID") String seriesID, ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
        Series s = db.seriesRepo.getById(seriesID);
        chapterList = s.getChapters();
        Gson gson = new GsonBuilder().create();
        json.add("chapterList", gson.toJsonTree(chapterList));
        json.add("series", gson.toJsonTree(s));
        return json;
    }

    @RequestMapping(value = "/chapter-index/subscribe", method = RequestMethod.GET)
    public @ResponseBody
    JsonObject getPageOptions(ModelMap model, HttpSession session, HttpServletRequest req) {
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();
        boolean isSubscribed = false;

        UserService userService = UserServiceFactory.getUserService();
        if(userService.isUserLoggedIn() == false) {
            return json;//"Error: User not logged in";
        }

        // load user from the datastore
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());
        if(user == null) {
            return json;//"Error: Unable to load user from datastore";
        }

        json.add("isSubscribed", gson.toJsonTree(isSubscribed));
        return json;
    }
}
