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

        if(userService.getCurrentUser() != null) {
            UserData user = null;
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
        System.out.println("Recently Viewed: " + recentlyViewed);
        ArrayList<Series> suggestions = null;

        if (recentlyViewed.size() > 0)
        {
            Series series = recentlyViewed.get(0);
            user.addSuggestions(series);
            suggestions = user.getSuggestions();
            System.out.println("Suggestions" + suggestions);
        }

        json.add("subscriptions", gson.toJsonTree(subscriptions));
        json.add("recentlyViewed", gson.toJsonTree(recentlyViewed));
        json.add("suggestions", gson.toJsonTree(suggestions));

        // Check if suggestions length is 0 on the frontend.




        return json;
    }
}
