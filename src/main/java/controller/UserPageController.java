package controller;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import data.DbContext;
import data.model.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class UserPageController {

    protected DbContext db = new DbContext();
    @RequestMapping(value="user", method = RequestMethod.GET)
    public String userPageController(ModelMap model) {
        System.out.println("Going to user-page ");
        return "user";
    }

    @RequestMapping(value = "/user/test/", method = RequestMethod.GET)
    public String getUserLookup(@RequestParam(value = "userID") String userID, ModelMap model, HttpSession session, HttpServletRequest req) {
        //http://localhost:8080/user/test/?userID=test.test.test.test

        System.out.println("User:" +  userID);
        return "user";
    }

    @RequestMapping(value="user/lookup/{userEmail}", method = RequestMethod.GET)
    public String loadUser(@PathVariable(value="userEmail") String userEmail, ModelMap model) {
        System.out.println("Going to user-page");
        return "user";
    }

    @RequestMapping(value="user/get/", method = RequestMethod.GET)
    public @ResponseBody
    JsonObject getUser(@RequestParam(value="userToLookUpEmail") String userToLookUpEmail, ModelMap model, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
        System.out.println("Got here");
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();

        System.out.println(userToLookUpEmail);

        // load user from the datastore
        UserData user = db.userRepo.getUserById(userToLookUpEmail);
        System.out.println(user);

        String nickname = user.getNickname();
        String about_me = user.getAboutMe();
        String userEmail = user.getEmail();
        ArrayList series_list = new ArrayList();

        System.out.println("userEmail: " + userEmail);

        json.add("nickname", gson.toJsonTree(nickname));
        json.add("about_me", gson.toJsonTree(about_me));
        series_list = user.getMySeries();
        json.add("series_list", gson.toJsonTree(series_list));

        System.out.println(json);

        //Load this users settings

        return json;
    }


}
