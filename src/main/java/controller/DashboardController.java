package controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.appengine.repackaged.com.google.gson.JsonObject;
import data.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import data.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mk on 3/31/16.
 */

@Controller
public class DashboardController {

    protected DbContext db = new DbContext();

    @RequestMapping(value="dashboard", method = RequestMethod.GET)
    public String dashboardController(HttpServletResponse response, ModelMap model) throws IOException {
        UserService userService = UserServiceFactory.getUserService();
        //UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());
        //String email = user.getNickname();
        UserData user = null;

        if(userService.getCurrentUser() != null) {
            System.out.println("Get Current User");

        String email = userService.getCurrentUser().getNickname();
        if(!db.userRepo.exists(email)){
            //DNE
            user = db.userRepo.create(email);
        } else {
            // exists
            System.out.println("user exists");
            user = db.userRepo.getUserById(email);
        }
        String nickname = user.getNickname();
        model.addAttribute("nickname", nickname);
        return "dashboard";
        }

        return "login";
    }

    @RequestMapping(value="dashboard/get/", method = RequestMethod.GET)
    public @ResponseBody JsonObject getSubscriptionsRecentSuggestions(ModelMap model) {
        System.out.println("In dashboard");
        JsonObject json = new JsonObject();
        Gson gson = new GsonBuilder().create();
        UserService userService = UserServiceFactory.getUserService();
        UserData user = db.userRepo.getUserById(userService.getCurrentUser().getEmail());

        ArrayList<Series> subscriptions = user.getSubscriptions();
        ArrayList<Series> recentlyViewed = user.getRecentlyViewed();

        ArrayList<Series> suggestions = user.getSuggestions();

        //Make suggestions algorithm here.



        json.add("subscriptions", gson.toJsonTree(subscriptions));
        json.add("recentlyViewed", gson.toJsonTree(recentlyViewed));

        return json;
    }

//    @RequestMapping(value="dashboard/", method = RequestMethod.GET)
//    public @ResponseBody
//    JsonObject getDashboard(@RequestParam(value="userToLookUpEmail") String userToLookUpEmail, ModelMap model, HttpSession session, HttpServletRequest req, HttpServletResponse res) {
//        System.out.println("Got here");
//        JsonObject json = new JsonObject();
//        Gson gson = new GsonBuilder().create();
//
//        System.out.println(userToLookUpEmail);
//
//        // load user from the datastore
//        UserData user = db.userRepo.getUserById(userToLookUpEmail);
//        System.out.println(user);
//
//        String nickname = user.getNickname();
//        String userEmail = user.getEmail();
//        ArrayList series_list = new ArrayList();
//
//        System.out.println("userEmail: " + userEmail);
//
//        json.add("nickname", gson.toJsonTree(nickname));
//        series_list = user.getMySeries();
//        json.add("series_list", gson.toJsonTree(series_list));
//
//        System.out.println(json);
//
//        //Load this users settings
//
//        return json;
//    }

}
